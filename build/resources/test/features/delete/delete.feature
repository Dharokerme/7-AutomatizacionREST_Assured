Feature: Borrar pagina
  Como administrador en el sistema
  necesito borrar paginas de usuarios que ya no se usan
  para poder tener informacion actualizada los usuarios

  Scenario: borrado exitoso
    Given el administrador esta en la pagina de borrar
    When el administrador presiona el boton borrar pagina
    Then el administrador deberia ver un codigo de repuesta exitoso (204 sin contenido).
