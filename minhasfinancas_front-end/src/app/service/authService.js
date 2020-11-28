import LocalStorageService from "./localStorageService";

export const USUARIO_LOGADO = "_usuario_logado";
export default class AuthService {
  static isUsuarioLogado() {
    const usuario = LocalStorageService.obterItem(USUARIO_LOGADO);
    return usuario && usuario.id;
  }

  static getUser() {
    return LocalStorageService.obterItem(USUARIO_LOGADO);
  }

  static logar(usuario) {
    LocalStorageService.addItem(USUARIO_LOGADO, usuario);
  }

  static removerUsuarioAutenticado() {
    LocalStorageService.removerItem(USUARIO_LOGADO);
  }
}
