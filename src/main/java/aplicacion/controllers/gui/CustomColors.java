package aplicacion.controllers.gui;

import java.awt.*;

/**
 * Enum con colores varios de utilidad para la interfaz gráfica.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 4.0
 */
public enum CustomColors {

    DANGER_RED {
        @Override
        public Color getColor() {
            return new Color(182, 78, 78);
        }
    },
    INFO_BLUE {
        @Override
        public Color getColor() {
            return new Color(40, 80, 165);
        }
    },
    QUESTION_YELLOW {
        @Override
        public Color getColor() {
            return new Color(201, 155, 68);
        }
    },
    MAIN_COLOR {
        @Override
        public Color getColor() {
            return new Color(215, 105, 0);
        }
    },
    // MAIN_COLOR {
    //     @Override
    //     public Color getColor() {
    //         return new Color(15, 135, 165);
    //     }
    // },
    LIGHT_COLOR {
        @Override
        public Color getColor() {
            return new Color(250, 250, 250);
        }

        @Override
        public Color getColor(boolean darkMode) {
            if (darkMode) {
                return new Color(5, 5, 5);
            }
            return new Color(250, 250, 250);
        }
    },
    DARK_COLOR {
        @Override
        public Color getColor() {
            return new Color(5, 5, 5);
        }

        @Override
        public Color getColor(boolean darkMode) {
            if (darkMode) {
                return new Color(250, 250, 250);
            }
            return new Color(5, 5, 5);
        }
    },
    LIGHT_BG {
        @Override
        public Color getColor() {
            return new Color(235, 237, 235);
        }
    },
    LIGHT_FG {
        @Override
        public Color getColor() {
            return new Color(35, 33, 35);
        }
    },
    DARK_BG {
        @Override
        public Color getColor() {
            return new Color(35, 33, 35);
        }
    },
    DARK_FG {
        @Override
        public Color getColor() {
            return new Color(235, 237, 235);
        }
    };

    CustomColors() {

    }

    public abstract Color getColor();

    public Color getColor(boolean darkMode) {
        return getColor();
    }
}