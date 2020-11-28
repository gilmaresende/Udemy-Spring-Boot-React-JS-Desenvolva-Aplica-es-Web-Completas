import React from "react";

import Login from "../views/login";
import CadastroUsuario from "../views/cadastroUsuario";
import ConsultaLancamento from "../views/lancamentos/consultaLancamentos";
import CadastroLancamento from "../views/lancamentos/cadastroLancamento";
import Home from "../views/home";
import { Route, Switch, HashRouter, Redirect } from "react-router-dom";
import { AuthConsumer } from "../main/provedorAutenticacao";

function RotaAutenticada({
  component: Component,
  isUsuarioAtenticado,
  ...props
}) {
  return (
    <Route
      {...props}
      render={(componentProps) => {
        if (isUsuarioAtenticado) {
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
function Rotas(props) {
  return (
    <HashRouter>
      <Switch>
        <Route path="/login" component={Login} />
        <Route path="/cadastro-usuario" component={CadastroUsuario} />
        <RotaAutenticada
          isUsuarioAtenticado={props.isUsuarioAtenticado}
          path="/home"
          component={Home}
        />
        <RotaAutenticada
          isUsuarioAtenticado={props.isUsuarioAtenticado}
          path="/consulta-lancamentos"
          component={ConsultaLancamento}
        />
        <RotaAutenticada
          isUsuarioAtenticado={props.isUsuarioAtenticado}
          path="/cadastro-lancamento/:id?"
          component={CadastroLancamento}
        />
      </Switch>
    </HashRouter>
  );
}

export default () => (
  <AuthConsumer>
    {(context) => (
      <Rotas
        isUsuarioAtenticado={context.isAutenticado}
      />
    )}
  </AuthConsumer>
);
