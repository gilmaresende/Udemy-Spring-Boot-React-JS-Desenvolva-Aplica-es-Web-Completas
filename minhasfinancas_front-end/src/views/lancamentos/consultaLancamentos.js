import React from "react";
import Card from "../../components/card";
import FormGroup from "../../components/form-group";
import SelectMenu from "../../components/selectMenu";
import LancamentoTable from "./lancamentosTable";
import LancamentoService from "../../app/service/lancamentoService";
import LocalStorageService from "../../app/service/localStorageService";
import * as menssages from "../../components/toastr";
import { Button } from "primereact/button";

import { Dialog } from "primereact/dialog";

class ConsultaLancamento extends React.Component {
  constructor() {
    super();
    this.service = new LancamentoService();
  }
  state = {
    ano: "",
    mes: "",
    tipo: "",
    descricao: "",
    showConfirDialog: false,
    lancamentoDeletar: {},
    lancamentos: [],
  };

  buscar = () => {
    if (!this.state.ano) {
      menssages.mensagemErro("Informe o Ano!");
      return false;
    }
    const userLogado = LocalStorageService.obterItem("_usuario_logado");
    const lancamentoFiltro = {
      ano: this.state.ano,
      mes: this.state.mes,
      tipo: this.state.tipo,
      descricao: this.state.descricao,
      usuario: userLogado.id,
    };

    this.service
      .consultar(lancamentoFiltro)
      .then((response) => {
        if (response.data.length < 1) {
          menssages.mensagemAlerta("Nenhum resultado encontrado!");
        }
        this.setState({ lancamentos: response.data });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  alterarStatus = (lancamento, status) => {
    this.service.alterarStatus(lancamento.id, status).then((mensagem) => {
      const lancamentos = this.state.lancamentos;
      const index = lancamentos.indexOf(lancamento);
      if (index !== -1) {
        lancamento.status = status;
        lancamento[index] = lancamento;
        this.setState({ lancamentos });
      }
      menssages.mensagemSucesso("Status Atualizado com Sucesso!");
    });
  };

  novoLancamento = () => {
    this.props.history.push("/cadastro-lancamento");
  };

  render() {
    const meses = this.service.obterListaMeses();
    const tiposLancamentos = this.service.obterListaTipos();

    const confirmeDialogFooter = (
      <div>
        <Button label="Confirma" icon="pi pi-check" onClick={this.delete} />
        <Button
          label="Cancelar"
          icon="pi pi-times"
          onClick={this.cancelarDelecao}
        />
      </div>
    );

    return (
      <Card title="Consulta Lançamentos">
        <div className="row">
          <div className="col-md-6">
            <div className="bs-component">
              <FormGroup htmlForm="inputAno" label="Ano: *">
                <input
                  type="text"
                  className="form-control"
                  id="inputAno"
                  value={this.state.ano}
                  onChange={(e) => this.setState({ ano: e.target.value })}
                  placeholder="Digite o Ano..."
                ></input>
              </FormGroup>
              <FormGroup htmlForm="inputMes" label="Mes:">
                <SelectMenu
                  id="inputMes"
                  className="form-control"
                  value={this.state.mes}
                  onChange={(e) => this.setState({ mes: e.target.value })}
                  lista={meses}
                ></SelectMenu>
              </FormGroup>
              <FormGroup htmlForm="inputDescricao" label="Descrição:">
                <input
                  type="text"
                  className="form-control"
                  id="inputDescricao"
                  value={this.state.descricao}
                  onChange={(e) => this.setState({ descricao: e.target.value })}
                  placeholder="Digite a Descrição..."
                ></input>
              </FormGroup>
              <FormGroup
                htmlForm="inputTipoLancamento"
                label="Tipo Lançamento: *"
              >
                <SelectMenu
                  id="inputTipoLancamento"
                  className="form-control"
                  value={this.state.tipo}
                  onChange={(e) => this.setState({ tipo: e.target.value })}
                  lista={tiposLancamentos}
                ></SelectMenu>
              </FormGroup>
              <button
                type="button"
                className="btn btn-success"
                onClick={this.buscar}
              >
                <i className="pi pi-search"></i> Buscar
              </button>
              <button
                type="button"
                className="btn btn-danger"
                onClick={this.novoLancamento}
              >
                <i className="pi pi-plus"></i> Cadastrar
              </button>
            </div>
          </div>
        </div>
        <br />
        <div className="row">
          <div className="col-md-12">
            <div className="bs-component">
              <LancamentoTable
                lancamentos={this.state.lancamentos}
                deleteAction={this.abrirConfirmacao}
                editAction={this.editar}
                alterarStatus={this.alterarStatus}
              ></LancamentoTable>
            </div>
          </div>
        </div>
        <div>
          <Dialog
            header="Confirmação!?"
            visible={this.state.showConfirDialog}
            style={{ width: "50vw" }}
            modal={true}
            onHide={() => this.setState({ showConfirDialog: false })}
            footer={confirmeDialogFooter}
          >
            <p>Confimar exclusão do lançamento?</p>
          </Dialog>
        </div>
      </Card>
    );
  }

  cancelarDelecao = () => {
    this.setState({ showConfirDialog: false, lancamentoDeletar: {} });
  };

  abrirConfirmacao = (lancamento) => {
    this.setState({ showConfirDialog: true, lancamentoDeletar: lancamento });
  };

  delete = () => {
    this.service
      .deletar(this.state.lancamentoDeletar.id)
      .then((response) => {
        const lancamentos = this.state.lancamentos;
        const index = lancamentos.indexOf(this.state.lancamentoDeletar);
        lancamentos.splice(index, 1);
        this.setState(lancamentos);
        this.setState({
          lancamentos: lancamentos,
          showConfirDialog: false,
          lancamentoDeletar: {},
        });
        menssages.mensagemSucesso("Lançamento deletado com Sucesso!");
      })
      .catch((error) => {
        menssages.mensagemErro("Erro ao deletar Lançamento!");
      });
  };

  editar = (id) => {
    this.props.history.push(`/cadastro-lancamento/${id}`);
  };
}

export default ConsultaLancamento;
