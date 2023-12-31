package br.ufrn.imd.controllers;

import br.ufrn.imd.DAO.UsuarioDAO;
import br.ufrn.imd.models.Usuario;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import java.sql.Connection;

//import java.awt.event.ActionEvent;

/**
 * Controlador da interface de login da aplicação.
 * Responsável por gerenciar as ações e eventos associados aos elementos da interface.
 */
public class LoginController implements Initializable {

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button ConfirmButton;

    @FXML
    private Label WarningTextLogin;
    private Usuario usuario;

    /**
     * Ação executada quando o botão de confirmação é pressionado.
     * Autentica o usuário e direciona para a página principal se as credenciais estiverem corretas.
     * Exibe uma mensagem de erro se as credenciais estiverem incorretas.
     */
    public void ConfirmButtonOnAction(){
        if(!usernameField.getText().isBlank() && !passwordField.getText().isBlank()){
            String emailUsuario = usernameField.getText();
            ConfirmButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    UsuarioDAO udao = new UsuarioDAO();
                    Usuario usuario = udao.getByEmail(emailUsuario);
                    if (udao.autenticar(usernameField.getText(), passwordField.getText())){
                        SceneAux.changeScene(actionEvent, "/MainPage.fxml", "Media Player", usuario);
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Usuário ou senha incorretos.");
                        alert.show();
                    }
                }
            });
        }
        else{
            WarningTextLogin.setText("Insira um usuário e senha.");
        }
    }

    @FXML
    private ImageView GuyHeadphonesLogin;

    @FXML
    private ImageView IconHeadPhone;

    @FXML
    private Button RegisterButton;


    public void RegisterButtonOnAction(ActionEvent e){

    }

    /**
     * Ação executada quando o botão de registro é pressionado.
     * Direciona para a página de registro.
     *
     * @param event O evento de ação que desencadeou a chamada do método.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RegisterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SceneAux.changeScene(event, "/RegistrationPage.fxml", "Tela de Registro", usuario);
            }
        });
    }
}
