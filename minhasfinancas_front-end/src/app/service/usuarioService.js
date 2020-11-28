import ApiService from "../apiservice";

import ErroValidacao from "./exceptions/erroValidacao";

class UsuarioService extends ApiService {
  constructor() {
    super("/api/usuario");
  }

  autenticar(credenciais) {
    return this.post("/autenticar", credenciais);
  }

  obterSaldoPorUsuario(id) {
    return this.get(`/${id}/saldo`);
  }

  salvar(usuario) {
    return this.post("/", usuario);
  }

  validar(usuario) {
    const errors = [];

    if (!usuario.nome) {
      errors.push("Campo nome é obrigatorio!");
    }
    if (!usuario.email) {
      errors.push("Campo email é obrigatorio!");
    } else if (!usuario.email.match(/^[a-z0-9.]+@[a-z0-9]+\.[a-z]/)) {
      errors.push("Email informado não é valido!");
    }
    if (!usuario.senha || !usuario.senhaRepetida) {
      errors.push("Informe a senha duas vezes!");
    } else if (usuario.senha !== usuario.senhaRepetida) {
      errors.push("Senhas não conhecidem!");
    }

    if (errors && errors.length > 0) {
      throw new ErroValidacao(errors);
    }
  }
}

export default UsuarioService;
