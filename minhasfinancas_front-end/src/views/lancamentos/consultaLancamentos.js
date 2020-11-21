//import { render } from "@testing-library/react";
import React from "react";
//import { withRouter } from "react-router-dom";
import Card from "../../components/card";
import FormGroup from "../../components/form-group";
import SelectMenu from "../../components/selectMenu";
import LancamentoTable from "./lancamentosTable";

class ConsultaLancamento extends React.Component {
  state = {
    ano: "",
    mes: "",
    tipo: "",
  };

  buscar = () => {
    console.log(this.state);
  };

  render() {
    const meses = [
      { label: "SELECIONE...", value: "" },
      { label: "Janeiro", value: "1" },
      { label: "Fevereiro", value: "2" },
      { label: "Março", value: "3" },
      { label: "Abril", value: "4" },
      { label: "Maio", value: "5" },
      { label: "Junho", value: "6" },
      { label: "Julho", value: "7" },
      { label: "Agosto", value: "8" },
      { label: "Setembro", value: "9" },
      { label: "Outubro", value: "10" },
      { label: "Novembro", value: "11" },
      { label: "Dezembro", value: "12" },
    ];

    const tiposLancamentos = [
      { label: "SELECIONE...", value: "" },
      { label: "Receita", value: "DESPESA" },
      { label: "Despesa", value: "RECEITA" },
    ];

    const lancamentos = [
      {
        id: 1,
        descricao: "Salario",
        valor: "5000",
        mes: "1",
        tipo: "Receita",
        status: "Efetivado",
      },
    ];
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
                  placeholder="Digite o Ano"
                ></input>
              </FormGroup>
              <FormGroup htmlForm="inputMes" label="Mes: *">
                <SelectMenu
                  id="inputMes"
                  className="form-control"
                  value={this.state.mes}
                  onChange={(e) => this.setState({ mes: e.target.value })}
                  lista={meses}
                ></SelectMenu>
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
                Buscar
              </button>
              <button type="button" className="btn btn-danger">
                Cadastrar
              </button>
            </div>
          </div>
        </div>
        <br />
        <div className="row">
          <div className="col-md-12">
            <div className="bs-component">
              <LancamentoTable lancamentos={lancamentos}></LancamentoTable>
            </div>
          </div>
        </div>
      </Card>
    );
  }
}

export default ConsultaLancamento;
