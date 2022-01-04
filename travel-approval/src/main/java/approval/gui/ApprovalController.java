package approval.gui;


import model.approval.ApprovalReply;
import model.approval.ApprovalRequest;
import shared.gui.ListViewLine;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class ApprovalController implements Initializable {

    @FXML
    private ListView<ListViewLine<ApprovalRequest, ApprovalReply>> lvRequestReply;
    @FXML
    private RadioButton rbApprove;
    @FXML
    private RadioButton rbReject;
    @FXML
    private Button btnSendReply;

    private final String approvalName;

    public ApprovalController(String approvalAppName, String approvalRequestQueue) {
        this.approvalName = approvalAppName;
    }

    private void sendApprovalReply() {
        ListViewLine<ApprovalRequest, ApprovalReply> rr = lvRequestReply.getSelectionModel().getSelectedItem();
        if (rr != null) {
            boolean approved = rbApprove.isSelected();
            String name = "";
            if (!approved) {
                name = approvalName;
            }
            ApprovalReply reply = new ApprovalReply(approved, name);
            rr.setReply(reply);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    lvRequestReply.refresh();
                }
            });
            // @TODO send the reply
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup radioButtonsGroup = new ToggleGroup();
        rbApprove.setToggleGroup(radioButtonsGroup);
        rbReject.setToggleGroup(radioButtonsGroup);
        btnSendReply.setOnAction(event -> sendApprovalReply());
    }

    public void stop() {
        // @TODO this is executed when closing this app
    }
}
