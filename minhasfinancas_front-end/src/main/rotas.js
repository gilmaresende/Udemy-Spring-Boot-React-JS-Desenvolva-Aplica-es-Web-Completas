import React from "react";

import Login from "../views/login";
import CadastroUsuario from "../views/cadastroUsuario";
import ConsultaLancamento from "../views/lancamentos/consultaLancamentos";
import CadastroLancamento from "../views/lancamentos/cadastroLancamento";
import Home from "../views/home";
import { Route, Switch, HashRouter } from "react-router-dom";

function Rotas() {
  return (
    <HashRouter>
      <Switch>
        <Route path="/home" component={Home} />
        <Route path="/login" component={Login} />
        <Route path="/cadastro-usuario" component={CadastroUsuario} />
        <Route path="/consulta-lancamentos" component={ConsultaLancamento} />
        <Route path="/cadastro-lancamento/:id?" component={CadastroLancamento} />
      </Switch>
    </HashRouter>
  );
}

export default Rotas;
