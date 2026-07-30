package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

public class CPUController {

    private final CPUService cpuService;

    private final Gson gson = new Gson();

    public CPUController(CPUService cpuService) {
        this.cpuService = cpuService;
    }

    // CORS helper
    private void addCORS(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.sendResponseHeaders(204, -1);
        }
    }

    public HttpHandler healthHandler() {
        return exchange -> {
            addCORS(exchange);
            sendJson(exchange, 200, "{\"ok\":true}");
        };
    }

    public HttpHandler stateHandler() {
        return exchange -> {
            addCORS(exchange);

            if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJson(exchange, 405, "{\"error\":\"Method Not Allowed\"}");
                return;
            }

            sendJson(exchange, 200, gson.toJson(cpuService.getState()));
        };
    }

    public HttpHandler stepHandler() {
        return exchange -> {
            addCORS(exchange);

            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJson(exchange, 405, "{\"error\":\"Method Not Allowed\"}");
                return;
            }

            sendJson(exchange, 200, gson.toJson(cpuService.step()));
        };
    }

    public HttpHandler resetHandler() {
        return exchange -> {
            addCORS(exchange);

            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJson(exchange, 405, "{\"error\":\"Method Not Allowed\"}");
                return;
            }

            cpuService.reset();
            sendJson(exchange, 200, gson.toJson(cpuService.getState()));
        };
    }

    public HttpHandler loadHandler() {
        return exchange -> {
            addCORS(exchange);

            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJson(exchange, 405, "{\"error\":\"Method Not Allowed\"}");
                return;
            }

            byte[] body = exchange.getRequestBody().readAllBytes();
            String text = new String(body, StandardCharsets.UTF_8).trim();

            int[] program = parseIntArray(text);
            cpuService.loadProgram(program);

            sendJson(exchange, 200, gson.toJson(cpuService.getState()));
        };
    }

    private static void sendJson(HttpExchange exchange, int status, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(status, bytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    private static int[] parseIntArray(String text) {
        text = text.trim();

        if (text.isEmpty() || text.equals("[]")) return new int[0];

        text = text.replace("[", "").replace("]", "").trim();

        if (text.isEmpty()) return new int[0];

        String[] parts = text.split(",");
        int[] out = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            out[i] = Integer.parseInt(parts[i].trim());
        }

        return out;
    }
}