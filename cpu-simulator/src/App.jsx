import React, { useState } from 'react';
import './App.css';

import RegisterPanel from './RegisterPanel';
import AluPanel from './ALUPanel';

const DEFAULT_ASSEMBLY = `; Simple Addition Program
MOV R1, #5
MOV R2, #10
ADD R3, R1, R2
STORE R3, 0x100`;

export default function App() {
  const [assemblyCode, setAssemblyCode] = useState(DEFAULT_ASSEMBLY);
  const [currentLine, setCurrentLine] = useState(0);
  const [selectedComponent, setSelectedComponent] = useState(null);
  const [isRunning, setIsRunning] = useState(false);

  const [cpuState, setCpuState] = useState({
    pc: '0x0002',
    registers: { R1: 5, R2: 10, R3: 15, R4: 0 },
    alu: { op: 'ADD', inA: 5, inB: 10, out: 15, flags: { Z: 0, N: 0 } },
    activeBus: 'ALU_TO_REG',
  });

  const handleStep = () => {
    const lines = assemblyCode.split('\n');
    setCurrentLine((prev) => (prev + 1) % lines.length);

    setCpuState((prev) => {
      const result = prev.registers.R1 + prev.registers.R2;

      return {
        ...prev,
        pc: `0x${(parseInt(prev.pc, 16) + 1).toString(16).padStart(4, '0')}`,
        registers: {
          ...prev.registers,
          R3: result,
        },
        alu: {
          ...prev.alu,
          op: 'ADD',
          inA: prev.registers.R1,
          inB: prev.registers.R2,
          out: result,
          flags: {
            Z: result === 0 ? 1 : 0,
            N: result < 0 ? 1 : 0,
          },
        },
        activeBus: 'ALU_TO_REG',
      };
    });
  };

  return (
    <div className="app-container">
      <section className="hero-section">
        <div className="hero-content">
          <h1>Virtual CPU Visualizer</h1>
          <p>Explore computer architecture from 3D hardware down to assembly logic.</p>
          <div className="scroll-indicator">Scroll down to explore ↓</div>
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
            <button className="btn secondary" onClick={() => setCurrentLine(0)}>
              ↺ Reset
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
                alu={cpuState.alu}
                selected={selectedComponent}
                onSelect={setSelectedComponent}
              />

              <RegisterPanel
                registers={cpuState.registers}
                selected={selectedComponent}
                onSelect={setSelectedComponent}
              />

              <div
                className={`cpu-node control-unit ${selectedComponent === 'CU' ? 'active' : ''}`}
                onClick={() => setSelectedComponent('CU')}
              >
                <h4>Control Unit</h4>
                <div className="node-val">PC: {cpuState.pc}</div>
              </div>

              <div className={`bus-line ${cpuState.activeBus}`}>
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
                  <p>Input A: {cpuState.alu.inA} | Input B: {cpuState.alu.inB}</p>
                  <p>Result: {cpuState.alu.out}</p>
                  <p>Flags: Z={cpuState.alu.flags.Z}, N={cpuState.alu.flags.N}</p>
                </div>
              )}

              {selectedComponent === 'REGISTERS' && (
                <div>
                  <p><strong>Register File:</strong> High-speed internal storage locations.</p>
                  <ul>
                    {Object.entries(cpuState.registers).map(([r, v]) => (
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