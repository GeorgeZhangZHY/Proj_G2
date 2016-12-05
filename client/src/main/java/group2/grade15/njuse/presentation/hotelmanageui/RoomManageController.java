package group2.grade15.njuse.presentation.hotelmanageui;

import group2.grade15.njuse.presentation.myanimation.Fade;
import group2.grade15.njuse.presentation.mycontrol.CustomeButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ALIENWARE-PC on 2016/12/4.
 */
public class RoomManageController implements Initializable {
    @FXML
    private Label addButton;
    @FXML
    private Label modifyButton;
    @FXML
    private Label deleteButton;

    @FXML
    private GridPane addPane;
    @FXML
    private GridPane modifyPane;
    @FXML
    private GridPane deletePane;
    @FXML
    private HBox checkPane;
    @FXML
    private Label check;
    @FXML
    private Label cancel;
    @FXML
    private Group opGroup;
    private GridPane now=new GridPane();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomeButton.implButton(check,"file:client/src/main/res/hotelmanage/Check");
        CustomeButton.implButton(cancel,"file:client/src/main/res/hotelmanage/Cancel");
        CustomeButton.implButton(addButton,"file:client/src/main/res/hotelmanage/add");
        CustomeButton.implButton(modifyButton,"file:client/src/main/res/hotelmanage/modify");
        CustomeButton.implButton(deleteButton,"file:client/src/main/res/hotelmanage/delete");

    }
    public void toAdd(){
        now.setVisible(false);
        now=addPane;
        now.setVisible(true);
        checkPane.setVisible(true);
        Fade in = new Fade(addPane, 200, true);
        in.play();
        Fade cin=new Fade(checkPane,200,true);
        cin.play();
    }
    public void toModify(){
        now.setVisible(false);
        now=modifyPane;
        now.setVisible(true);
        checkPane.setVisible(true);
        Fade in = new Fade(modifyPane, 200, true);
        in.play();
        Fade cin=new Fade(checkPane,200,true);
        cin.play();
    }
    public void toDelete(){
        now.setVisible(false);
        now=deletePane;
        now.setVisible(true);
        checkPane.setVisible(true);
        Fade in = new Fade(deletePane, 200, true);
        in.play();
        Fade cin=new Fade(checkPane,200,true);
        cin.play();
    }
    public void back(){
        now.setVisible(false);
        checkPane.setVisible(false);
    }
}