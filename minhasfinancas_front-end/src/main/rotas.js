import React from "react";

import Login from "../views/login";
import CadastroUsuario from "../views/cadastroUsuario";
import ConsultaLancamento from "../views/lancamentos/consultaLancamentos";
import CadastroLancamento from "../views/lancamentos/cadastroLancamento";
import AuthService from "../app/service/authService";

import Home from "../views/home";
import { Route, Switch, HashRouter, Redirect } from "react-router-dom";

function RotaAutenticada({ component: Component, ...props }) {
  return (
    <Route
      {...props}
      render={(componentProps) => {
        if (AuthService.isUsuarioLogado()) {
          return <Component {...componentProps}></Component>;
        } else {
          return (
            <Redirect
              to={{
                pathname: "/login",
                state: { from: componentProps.location },
              }}
            ></Redirect>
          );
        }
      }}
    />
  );
}
function Rotas() {
  return (
    <HashRouter>
      <Switch>
        <Route path="/login" component={Login} />
        <Route path="/cadastro-usuario" component={CadastroUsuario} />
        <RotaAutenticada path="/home" component={Home} />
        <RotaAutenticada
          path="/consulta-lancamentos"
          component={ConsultaLancamento}
        />
        <RotaAutenticada
          path="/cadastro-lancamento/:id?"
          component={CadastroLancamento}
        />
      </Switch>
    </HashRouter>
  );
}

export default Rotas;
