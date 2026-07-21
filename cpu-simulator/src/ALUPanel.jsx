// AluPanel.jsx
export default function AluPanel({ alu, selected, onSelect }) {
  return (
    <section className={`state-panel ${selected === 'ALU' ? 'active' : ''}`}>
      <div className="state-panel-header">
        <h3>ALU</h3>
        <span className="state-badge">{alu.op}</span>
      </div>

      <div className="alu-box">
        <div className="alu-row">
          <span>Input A</span>
          <strong>{alu.inA}</strong>
        </div>
        <div className="alu-row">
          <span>Input B</span>
          <strong>{alu.inB}</strong>
        </div>
        <div className="alu-row result">
          <span>Output</span>
          <strong>{alu.out}</strong>
        </div>
        <div className="alu-row">
          <span>Flags</span>
          <strong>Z:{alu.flags.Z} N:{alu.flags.N}</strong>
        </div>

        <button className="inspect-btn" onClick={() => onSelect?.('ALU')}>
          Inspect ALU
        </button>
      </div>
    </section>
  );
}