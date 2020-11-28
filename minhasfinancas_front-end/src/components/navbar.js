import React from "react";
import NavbarItem from "./navbarItem";
import AuthService from "../app/service/authService";

const isUsuarioAutenticado = () => {
  return AuthService.isUsuarioLogado();
};

const removeUser = () => {
  AuthService.removerUsuarioAutenticado();
};
function Navbar() {
  return (
    <div className="navbar navbar-expand-lg fixed-top navbar-dark bg-primary">
      <div className="container">
        <a href="https://bootswatch.com/" className="navbar-brand">
          Minhas Finanças
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarResponsive"
          aria-controls="navbarResponsive"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarResponsive">
          <ul className="navbar-nav">
            <NavbarItem
              render={isUsuarioAutenticado()}
              href="#/home"
              label="Home"
            />
            <NavbarItem
              render={isUsuarioAutenticado()}
              href="#/cadastro-usuario"
              label="Usuários"
            />
            <NavbarItem
              render={isUsuarioAutenticado()}
              href="#/consulta-lancamentos"
              label="Lançamentos"
            />
            <NavbarItem
              onClick={removeUser}
              render={isUsuarioAutenticado()}
              href="#/login"
              label="Sair"
            />
          </ul>
        </div>
      </div>
    </div>
  );
}

export default Navbar;
