// RegisterPanel.jsx
export default function RegisterPanel({ registers, selected, onSelect }) {
  return (
    <section className={`state-panel ${selected === 'REGISTERS' ? 'active' : ''}`}>
      <div className="state-panel-header">
        <h3>Register File</h3>
        <span className="state-badge">4 registers</span>
      </div>

      <div className="register-grid">
        {Object.entries(registers).map(([reg, value]) => (
          <button
            key={reg}
            className="register-chip"
            onClick={() => onSelect?.(`Register ${reg}`)}
          >
            <span>{reg}</span>
            <strong>{value}</strong>
          </button>
        ))}
      </div>
    </section>
  );
}