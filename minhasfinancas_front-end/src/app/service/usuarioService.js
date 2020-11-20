import ApiService from "../apiservice";

class UsuarioService extends ApiService {
  constructor() {
    super("/api/usuario");
  }

  autenticar(credenciais) {
    return this.post("/autenticar", credenciais);
  }
}

export default UsuarioService;
