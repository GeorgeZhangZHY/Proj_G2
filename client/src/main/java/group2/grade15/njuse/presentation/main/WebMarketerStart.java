package group2.grade15.njuse.presentation.main;

import group2.grade15.njuse.presentation.loginui.WebMarketerLoginController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created by ALIENWARE-PC on 2016/12/1.
 */
public class WebMarketerStart extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader(new URL("file:client/src/main/java/group2/grade15/njuse/presentation/loginui/WebMarketerLogin.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.setTitle("酒店预订系统——网站营销人员端");
        WebMarketerLoginController controller=loader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();
    }

    public static void mian(String[] args){
        launch(args);
    }
}