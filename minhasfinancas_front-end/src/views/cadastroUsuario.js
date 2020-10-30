import React from "react";

import Card from "../components/card";
import FormGroup from "../components/form-group";

class CadastroUsuario extends React.Component {
  state = { nome: "", email: "", senha: "", senhaRepetida: "" };

  salvar = () => {
    if (this.state.senha === this.state.senhaRepetida) {
    } else {
      console.log("Senhas não conhecidem!");
    }
  };

  render() {
    return (
      <div className="container">
        <Card title="Cadastro de Usuario">
          <div className="row">
            <div className="col-lg-12">
              <div className="bs-component">
                <FormGroup label="Nome*" htmlFor="inputNome">
                  <input
                    type="text"
                    id="inputNome"
                    className="form-control"
                    name="nome"
                    onChange={(e) => this.setState({ nome: e.target.value })}
                  ></input>
                </FormGroup>
                <FormGroup label="e-mail*" htmlFor="inputEmail">
                  <input
                    type="email"
                    className="form-control"
                    id="inputEmail"
                    name="email"
                    onChange={(e) => this.setState({ email: e.target.value })}
                  ></input>
                </FormGroup>
                <FormGroup label="Senha *" htmlFor="inputSenha">
                  <input
                    className="form-control"
                    type="password"
                    id="senha"
                    name="senha"
                    onChange={(e) => this.setState({ senha: e.target.value })}
                  ></input>
                </FormGroup>
                <FormGroup label="Repetir Senha*" htmlFor="inputSenhaRepetida">
                  <input
                    className="form-control"
                    type="password"
                    id="senhaRepetida"
                    name="senhaRepetida"
                    onChange={(e) =>
                      this.setState({ senhaRepetida: e.target.value })
                    }
                  />
                </FormGroup>
                <button className="btn btn-success" onClick={this.salvar}>
                  Salvar
                </button>
                <button className="btn btn-danger" onClick={this.salvar}>
                  Cancelar
                </button>
              </div>
            </div>
          </div>
        </Card>
      </div>
    );
  }
}

export default CadastroUsuario;
