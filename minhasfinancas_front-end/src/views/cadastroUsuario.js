import React from "react";

import Card from "../components/card";
import FormGroup from "../components/form-group";
import { withRouter } from "react-router-dom";
import UsuarioService from "../app/service/usuarioService";
import { mensagemErro, mensagemSucesso } from "../components/toastr";

class CadastroUsuario extends React.Component {
  state = { nome: "", email: "", senha: "", senhaRepetida: "" };

  constructor() {
    super();
    this.service = new UsuarioService();
  }

  salvar = () => {
    const { nome, email, senha, senhaRepetida } = this.state;
    const usuario = { nome, email, senha, senhaRepetida };

    try {
      this.service.validar(usuario);
    } catch (error) {
      const msgs = error.mensagens;
      msgs.forEach((msg) => mensagemErro(msg));
      return false;
    }

    this.service
      .salvar(usuario)
      .then((response) => {
        mensagemSucesso("Usuario Cadastrado com Sucesso! Faça o login!");
        this.props.history.push("/login");
      })
      .catch((erro) => {
        mensagemErro(erro.response.data);
      });
  };

  cancelar = () => {
    this.props.history.push("/login");
  };

  render() {
    return (
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
                <i className="pi - pi-save"></i> Salvar
              </button>
              <button className="btn btn-danger" onClick={this.cancelar}>
                <i className="pi - pi-times"></i> Cancelar
              </button>
            </div>
          </div>
        </div>
      </Card>
    );
  }
}

export default withRouter(CadastroUsuario);
