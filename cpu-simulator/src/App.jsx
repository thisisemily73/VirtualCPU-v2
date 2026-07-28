import React, { useEffect, useState } from 'react';
import './App.css';

import RegisterPanel from './RegisterPanel';
import AluPanel from './ALUPanel';

const DEFAULT_ASSEMBLY = `; Simple Addition Program
LOAD R1, 5
LOAD R2, 10
ADD R3, R1, R2
STORE R3, 0x100`;

const EMPTY_CPU_STATE = {
  pc: 0,
  registers: [0, 0, 0, 0, 0, 0, 0, 0],
  alu: 0,
  zeroFlag: false,
  carryFlag: false,
  negativeFlag: false,
  currentInstruction: 0,
  currentLine: 0,
  halted: false,
};

export default function App() {
  const [assemblyCode, setAssemblyCode] = useState(DEFAULT_ASSEMBLY);
  const [currentLine, setCurrentLine] = useState(0);
  const [selectedComponent, setSelectedComponent] = useState(null);
  const [isRunning, setIsRunning] = useState(false);
  const [cpuState, setCpuState] = useState(EMPTY_CPU_STATE);
  const [status, setStatus] = useState('idle');

  const api = async (url, options = {}) => {
    const res = await fetch(url, {
      headers: {
        'Content-Type': 'application/json',
        ...(options.headers || {}),
      },
      ...options,
    });

    if (!res.ok) {
      throw new Error(`Request failed: ${res.status}`);
    }

    return res.json();
  };

  const syncState = async () => {
    setStatus('loading state');

    try {
      const res = await fetch(
        'http://localhost:8080/api/cpu/state'
      );

      const data = await res.json();

      setCpuState(data);
      setCurrentLine(data.currentLine ?? 0);
      setStatus('state loaded');

    } catch (err) {
      setStatus(`error: ${err.message}`);
    }
  };

  // useEffect(() => {
  //   syncState();
  // }, []);

  useEffect(() => {
    if (!isRunning) return;

    const id = setInterval(() => {
      handleStep();
    }, 500);

    return () => clearInterval(id);
  }, [isRunning]);

  const handleStep = async () => {
    console.log("CLICKED STEP");

    try {
      const res = await fetch(
        'http://localhost:8080/api/cpu/step',
        {
          method: 'POST'
        }
      );

      const data = await res.json();

      console.log("DATA:", data);

      setCpuState(data);
      setCurrentLine(data.currentLine ?? 0);

    } catch (err) {
      console.error(err);
    }
  };

  const handleReset = async () => {
    setStatus('resetting');
    try {
      const data = await api(
        'http://localhost:8080/api/cpu/reset',
        { method: 'POST' }
      );
      setCpuState(data);
      setCurrentLine(0);
      setIsRunning(false);
      setStatus('reset done');
    } catch (err) {
      setStatus(`error: ${err.message}`);
    }
  };

  const handleLoad = async () => {
    const program = [2309, 32768]; // temporary real program

    const res = await fetch("http://localhost:8080/api/cpu/load", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(program)
    });

    const data = await res.json();
    setCpuState(data);
  };

  const registersObj = Object.fromEntries(
    (cpuState.registers || []).map((v, i) => [`R${i + 1}`, v])
  );

  const aluObj = {
    op: 'ADD',
    inA: cpuState.registers?.[0] ?? 0,
    inB: cpuState.registers?.[1] ?? 0,
    out: cpuState.alu ?? 0,
    flags: {
      Z: cpuState.zeroFlag ? 1 : 0,
      N: cpuState.negativeFlag ? 1 : 0,
    },
  };

  const activeBus = cpuState.halted ? 'HALTED' : 'ALU_TO_REG';

  return (
    <div className="app-container">
      <section className="hero-section">
        <div className="hero-content">
          <h1>Virtual CPU Visualizer</h1>
          <p>Explore computer architecture from 3D hardware down to assembly logic.</p>
          <div className="scroll-indicator">Scroll down to explore ↓</div>
          <div className="badge">{status}</div>
        </div>

        <div className="hero-3d-placeholder">
          <div className="cube-mock">[ 3D Chip Model Viewport ]</div>
        </div>
      </section>

      <section className="workbench-section">
        <header className="workbench-header">
          <h2>Interactive Execution Workbench</h2>
          <div className="control-bar">
            <button className="btn primary" onClick={handleStep}>
              ⏭ Step Cycle
            </button>
            <button
              className={`btn ${isRunning ? 'danger' : 'success'}`}
              onClick={() => setIsRunning(!isRunning)}
            >
              {isRunning ? '⏸ Pause' : '▶ Run'}
            </button>
            <button className="btn secondary" onClick={handleReset}>
              ↺ Reset
            </button>
            <button className="btn secondary" onClick={handleLoad}>
              ⬆ Load Program
            </button>
          </div>
        </header>

        <div className="split-view">
          <div className="panel left-panel">
            <div className="panel-header">
              <h3>CPU State Panels</h3>
              <span className="badge">Click any component to inspect</span>
            </div>

            <div className="state-stack">
              <AluPanel
                alu={aluObj}
                selected={selectedComponent}
                onSelect={setSelectedComponent}
              />

              <RegisterPanel
                registers={registersObj}
                selected={selectedComponent}
                onSelect={setSelectedComponent}
              />

              <div
                className={`cpu-node control-unit ${selectedComponent === 'CU' ? 'active' : ''}`}
                onClick={() => setSelectedComponent('CU')}
              >
                <h4>Control Unit</h4>
                <div className="node-val">
                  PC: 0x{Number(cpuState.pc || 0).toString(16).padStart(4, '0')}
                </div>
              </div>

              <div className={`bus-line ${activeBus}`}>
                <span className="bus-label">Data Bus</span>
              </div>
            </div>
          </div>

          <div className="panel right-panel">
            <div className="panel-header">
              <h3>Assembly Code</h3>
              <span className="badge">Line {currentLine + 1}</span>
            </div>

            <div className="code-editor-container">
              <div className="line-numbers">
                {assemblyCode.split('\n').map((_, idx) => (
                  <div
                    key={idx}
                    className={`line-num ${idx === currentLine ? 'active-line-num' : ''}`}
                  >
                    {idx + 1}
                  </div>
                ))}
              </div>

              <textarea
                className="code-textarea"
                value={assemblyCode}
                onChange={(e) => setAssemblyCode(e.target.value)}
                spellCheck="false"
              />
            </div>
          </div>
        </div>

        {selectedComponent && (
          <div className="inspector-drawer">
            <div className="drawer-header">
              <h3>Component Detail: {selectedComponent}</h3>
              <button className="close-btn" onClick={() => setSelectedComponent(null)}>
                ×
              </button>
            </div>

            <div className="drawer-content">
              {selectedComponent === 'ALU' && (
                <div>
                  <p><strong>Arithmetic Logic Unit:</strong> Performs math and logic operations.</p>
                  <p>Input A: {aluObj.inA} | Input B: {aluObj.inB}</p>
                  <p>Result: {aluObj.out}</p>
                  <p>Flags: Z={aluObj.flags.Z}, N={aluObj.flags.N}</p>
                </div>
              )}

              {selectedComponent === 'REGISTERS' && (
                <div>
                  <p><strong>Register File:</strong> High-speed internal storage locations.</p>
                  <ul>
                    {Object.entries(registersObj).map(([r, v]) => (
                      <li key={r}>
                        <strong>{r}:</strong> {v}
                      </li>
                    ))}
                  </ul>
                </div>
              )}

              {selectedComponent === 'CU' && (
                <div>
                  <p><strong>Control Unit:</strong> Decodes instructions and directs data paths.</p>
                  <p>Current Program Counter: {cpuState.pc}</p>
                </div>
              )}
            </div>
          </div>
        )}
      </section>
    </div>
  );
}