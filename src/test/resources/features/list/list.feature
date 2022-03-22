  Feature: Lista de usuarios
  Como administrador en la pagina
  necesito ver en cierta pagina la lista de usuarios
  para poder saber que usuarios hay en el sistema

  Scenario: Lista de usuarios
    Given el administrador esta en la pagina de inicio
    When el administrador hace click en el boton de lista de usuarios
    Then el administrador vera una lista de usuarios
