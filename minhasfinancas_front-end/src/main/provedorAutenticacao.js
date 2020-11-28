import React from "react";
import AuthService from "../app/service/authService";

export const AuthContext = React.createContext();
export const AuthConsumer = AuthContext.Consumer;
const AuthProvider = AuthContext.Provider;

class PrevedorAutenticacao extends React.Component {
  state = {
    usuarioAutenticado: null,
    isAutenticado: false,
  };

  iniciarSessao = (usuario) => {
    AuthService.logar(usuario);
    this.setState({ isAutenticado: true, usuarioAutenticado: usuario });
  };

  getSessao = async () => {
    const logado = AuthService.isUsuarioLogado();
    if (logado) {
      this.setState({
        isAutenticado: true,
        usuarioAutenticado: AuthService.getUser(),
      });
    }
  };

  encerrarSessao = () => {
    AuthService.removerUsuarioAutenticado();
    this.setState({ isAutenticado: false, usuarioAutenticado: null });
    console.log(this.state);
  };
  render() {
    const contexto = {
      usuarioAutenticado: this.state.usuarioAutenticado,
      isAutenticado: this.state.isAutenticado,
      encerrarSessao: this.encerrarSessao,
      iniciarSessao: this.iniciarSessao,
      getSessao: this.getSessao,
    };
    return <AuthProvider value={contexto}>{this.props.children}</AuthProvider>;
  }
}

export default PrevedorAutenticacao;
