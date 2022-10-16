export const BASE_URL_ME_LEVA_AI = "http://localhost:10000/meLevaAi";
export const PERSON_STATUS_AVAILABLE = "LIVRE";
export const PERSON_STATUS_BUSY = "OCUPADO";
export const PERSON_STATUS_INACTIVE = "INATIVO";
export const DRIVER_STATUS_START_RIDE = "ACEITAR";
export const DRIVER_STATUS_END_RIDE = "FINALIZAR";
export const SCORE = "SCORE";
export const TOAST_ERROR_TIME = 1500;

export const DRIVER_SELECTION_URL = "/selecionarMotorista";
export const SELECTED_DRIVER_URL = "/motorista";
export const INIT_RIDE = "/iniciarCorrida";
export const FINISH_RIDE = "/finalizarCorrida";
export const CREATE_RIDE = "/criarCorrida";
export const PASSENGER_ASSES = "/avaliacoesDoCliente";
export const PASSENGER_MENU = "/passageiroMenu";
export const DRIVER_MENU = "/motoristaMenu";
export const PASSENGER_PRINTER = "/depositarDinheiro";
export const DEACTIVATE_PASSENGER = "/desativarPassageiro";
export const DRIVER_ASSES_URL = "/avaliarPassageiros";
export const DRIVER_MENU_URL = "/motoristaMenu";
export const DRIVER_WITHDRAW_URL = "/sacarDinheiro";
export const DEACTIVATE_DRIVER_URL = "/desativarMotorista";

export const ARRAY_PASSENGER_URL = [
  { url: "/", name: "Menu" },
  { url: CREATE_RIDE, name: "Criar Corrida" },
  { url: PASSENGER_ASSES, name: "Avaliaçôes do Passageiro" },
  { url: PASSENGER_PRINTER, name: "Depositar dinheiro" },
  { url: DEACTIVATE_PASSENGER, name: "Desativar Passageiro" },
];

export const GET_ALL_DRIVERS_API = () => {
  return "/motoristas";
};
export const DRIVER_WITHDRAW_API = () => {
  return "/retirarDinheiro";
};
export const GET_SPECIFIC_DRIVER_API = (id) => {
  return `/motoristas/${id}`;
};
export const DRIVER_ASSES_API = () => {
  return "/driverAvalia";
};
