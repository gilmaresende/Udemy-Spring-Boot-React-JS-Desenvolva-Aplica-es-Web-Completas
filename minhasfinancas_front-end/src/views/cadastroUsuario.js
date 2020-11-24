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
    const msgs = this.validar();

    if (msgs && msgs.length > 0) {
      msgs.forEach((msg, index) => {
        mensagemErro(msg);
      });
      return false;
    }

    const usuario = {
      nome: this.state.nome,
      email: this.state.email,
      senha: this.state.senha,
    };
    this.service
      .salvar(usuario)
      .then((response) => {
        this.props.history.push("/consulta-lancamentos");
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

  validar() {
    const mensagens = [];

    if (!this.state.nome) {
      mensagens.push("Campo nome é obrigatorio!");
    }
    if (!this.state.email) {
      mensagens.push("Campo email é obrigatorio!");
    } else if (!this.state.email.match(/^[a-z0-9.]+@[a-z0-9]+\.[a-z]/)) {
      mensagens.push("Email informado não é valido!");
    }
    if (!this.state.senha || !this.state.senhaRepetida) {
      mensagens.push("Informe a senha duas vezes!");
    } else if (this.state.senha !== this.state.senhaRepetida) {
      mensagens.push("Senhas não conhecidem!");
    }

    return mensagens;
  }

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
                Salvar
              </button>
              <button className="btn btn-danger" onClick={this.cancelar}>
                Cancelar
              </button>
            </div>
          </div>
        </div>
      </Card>
    );
  }
}

export default withRouter(CadastroUsuario);
