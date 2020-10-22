import React from "react";

class App extends React.Component {
  state = {
    nome: null,
  };

  render() {
    return (
      <div>
        <label>Nome:</label>
        <input
          type="text"
          //value={this.state.nome}
          onChange={(e) => this.setState({ nome: e.target.value })}
        ></input>
        Ola {this.state.nome}
        <br />
      </div>
    );
  }
}

export default App;
