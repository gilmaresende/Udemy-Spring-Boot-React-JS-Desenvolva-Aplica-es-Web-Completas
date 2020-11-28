import React from "react";
import Formatter from "currency-formatter";
export default (props) => {
  const rows = props.lancamentos.map((lancamento) => {
    return (
      <tr key={lancamento.id}>
        <td>{lancamento.descricao}</td>
        <td>{Formatter.format(lancamento.valor, { locale: "pt-BR" })}</td>
        <td>{lancamento.tipo}</td>
        <td>{lancamento.mes}</td>
        <td>{lancamento.status}</td>
        <td>
          <button
            className="btn btn-success"
            onClick={(e) => props.alterarStatus(lancamento, "EFETIVADO")}
            type="button"
            title="Efetivar"
            disabled={lancamento.status === "EFETIVADO"}
          >
            <i className="pi pi-check p-mr-2"></i>
          </button>
          <button
            className="btn btn-warning"
            onClick={(e) => props.alterarStatus(lancamento, "CANCELADO")}
            type="button"
            title="Cancelar"
            disabled={lancamento.status === "CANCELADO"}
          >
            <i className="pi pi-ban"></i>
          </button>
          <button
            type="button"
            className="btn btn-primary"
            onClick={(e) => props.editAction(lancamento.id)}
            title="Editar"
          >
            <i className="pi pi-pencil"></i>
          </button>
          <button
            type="button"
            className="btn btn-danger"
            onClick={(e) => props.deleteAction(lancamento)}
            title="Deletar"
          >
            <i className="pi pi-trash"></i>
          </button>
        </td>
        <td></td>
      </tr>
    );
  });

  return (
    <table className="table table-hover">
      <thead>
        <tr>
          <th>Descrição</th>
          <th scope="col">Valor</th>
          <th scope="col">Tipo</th>
          <th scope="col">Mês</th>
          <th scope="col">Situação</th>
          <th scope="col">Ações</th>
        </tr>
      </thead>
      <tbody>{rows}</tbody>
    </table>
  );
};
