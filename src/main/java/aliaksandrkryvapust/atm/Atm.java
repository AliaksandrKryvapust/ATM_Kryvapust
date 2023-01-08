package aliaksandrkryvapust.atm;

import aliaksandrkryvapust.atm.controller.AtmController;
import aliaksandrkryvapust.atm.controller.api.IAtmController;

public class Atm {
    private static final IAtmController atmController = new AtmController();

    public static void main(String[] args) {
        atmController.execute();
    }
}
