package aplicacion.controllers.gui;

import javax.swing.*;

public enum Panels {
    HOLA {
        @Override
        public JPanel getPanel() {
            return null;
        }
    },
    INICIO {
        @Override
        public JPanel getPanel() {
            return null;
        }
    };

    public abstract JPanel getPanel();

}
