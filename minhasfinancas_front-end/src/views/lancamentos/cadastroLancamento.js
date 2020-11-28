import React from "react";
import Card from "../../components/card";
import FormGroup from "../../components/form-group";
import SelectMenu from "../../components/selectMenu";
import LancamentoService from "../../app/service/lancamentoService";
import { mensagemErro, mensagemSucesso } from "../../components/toastr";
import LocalStotaService from "../../app/service/localStorageService";

import { withRouter } from "react-router-dom";
class CadastroLancamento extends React.Component {
  state = {
    id: null,
    descricao: "",
    valor: "",
    mes: "",
    ano: "",
    tipo: "",
    status: "",
    usuario: null,
    atualizadando: false,
  };
  constructor() {
    super();
    this.service = new LancamentoService();
  }
  cancelar = () => {
    this.props.history.push("/consulta-lancamentos");
  };

  submit = () => {
    const { descricao, valor, mes, ano, tipo } = this.state;
    const lancamento = {
      descricao,
      valor,
      mes,
      ano,
      tipo,
      usuario: LocalStotaService.obterItem("_usuario_logado").id,
    };

    try {
      this.service.validar(lancamento);
    } catch (erro) {
      const erros = erro.mensagens;
      erros.forEach((msg) => {
        mensagemErro(msg);
      });
      return false;
    }
    this.service
      .salvar(lancamento)
      .then((response) => {
        this.props.history.push("/consulta-lancamentos");
        mensagemSucesso("Lancamento Cadastrado com Sucesso!");
      })
      .catch((erro) => {
        mensagemErro(erro.response.data);
      });
  };

  atualizar = () => {
    const {
      descricao,
      valor,
      mes,
      ano,
      tipo,
      usuario,
      id,
      status,
    } = this.state;
    const lancamento = {
      descricao,
      valor,
      mes,
      ano,
      tipo,
      usuario,
      id,
      status,
    };

    this.service
      .atualizar(lancamento)
      .then((response) => {
        this.props.history.push("/consulta-lancamentos");
        mensagemSucesso("Lancamento Atualizado com Sucesso!");
      })
      .catch((erro) => {
        mensagemErro(erro.response.data);
      });
  };

  handleChange = (event) => {
    const value = event.target.value;
    const name = event.target.name;
    this.setState({ [name]: value });
  };

  componentDidMount() {
    const parans = this.props.match.params;
    if (parans.id) {
      this.service
        .obterPorId(parans.id)
        .then((response) => {
          this.setState({ ...response.data, atualizadando: true });
        })
        .catch((erros) => {
          mensagemErro(erros.response.data);
        });
    }
  }

  render() {
    const listaTipo = this.service.obterListaTipos();
    const listaMeses = this.service.obterListaMeses();

    return (
      <Card
        title={
          this.state.atualizadando ? "Editando Lançamentos" : "Novo Lançamento"
        }
      >
        <div className="row">
          <div className="col-md-12">
            <FormGroup id="inputDescricao" label="Descricao: *">
              <input
                id="inputDescricao"
                type="text"
                name="descricao"
                onChange={this.handleChange}
                value={this.state.descricao}
                className="form-control"
              ></input>
            </FormGroup>
          </div>
        </div>
        <div className="row">
          <div className="col-md-6">
            <FormGroup id="inputAno" label="Ano: *">
              <input
                id="inputAno"
                type="text"
                className="form-control"
                name="ano"
                onChange={this.handleChange}
                value={this.state.ano}
              ></input>
            </FormGroup>
          </div>
          <div className="col-md-6">
            <FormGroup id="inputMes" label="Mês: *">
              <SelectMenu
                id="inputMes"
                className="form-control"
                lista={listaMeses}
                name="mes"
                onChange={this.handleChange}
                value={this.state.mes}
              ></SelectMenu>
            </FormGroup>
          </div>
        </div>
        <div className="row">
          <div className="col-md-4">
            <FormGroup id="inputValor" label="Valor: *">
              <input
                id="inputValor"
                type="text"
                className="form-control"
                name="valor"
                onChange={this.handleChange}
                value={this.state.valor}
              ></input>
            </FormGroup>{" "}
          </div>
          <div className="col-md-4">
            <FormGroup id="inputTipo" label="Tipo: *">
              <SelectMenu
                id="inputTipoLancamento"
                className="form-control"
                lista={listaTipo}
                name="tipo"
                onChange={this.handleChange}
                value={this.state.tipo}
              ></SelectMenu>
            </FormGroup>
          </div>
          <div className="col-md-4">
            <FormGroup id="inputStatus" label="Status:">
              <input
                type="text"
                className="form-control"
                disabled={true}
                name="status"
                onChange={this.handleChange}
                value={this.state.status}
              ></input>
            </FormGroup>
          </div>
        </div>
        <div className="row">
          <div className="col-md-6">
            {this.state.atualizadando ? (
              <button className="btn btn-primary" onClick={this.atualizar}>
                <i className="pi - pi-refresh"></i> Atualizar
              </button>
            ) : (
              <button className="btn btn-success" onClick={this.submit}>
              <i className="pi - pi-save"></i>  Salvar
              </button>
            )}
            <button className="btn btn-danger" onClick={this.cancelar}>
            <i className="pi - pi-backward"></i>  Cancelar
            </button>
          </div>
        </div>
      </Card>
    );
  }
}

export default withRouter(CadastroLancamento);
