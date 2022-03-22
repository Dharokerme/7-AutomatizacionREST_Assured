  Feature: Crear usuario
  Como nuevo trabajador en la pagina
  necesito crear mi usuario
  para poder acceder a los servicios

  Scenario: creacion exitosa
    Given el trabajador va a crear un usuario "morpheus" con puesto "leader"
    When el trabajador presiona el boton crear usuario
    Then el trabajador deberia ver un codigo de repuesta exitoso (201 creado) y su informacion.
