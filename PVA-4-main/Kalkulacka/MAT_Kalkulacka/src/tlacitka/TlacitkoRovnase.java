package tlacitka;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import kalkulacka1.Model;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class TlacitkoRovnase implements I_Algoritmus {
    private final Model mujModel;
    public TlacitkoRovnase() {
        mujModel = Model.getInstance();
    }
    @Override
    public void Algoritmus() {
        zpusob2();
    }
    private void zpusob1() {
        if (mujModel.getRadek1().equals("")) return;
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine js = manager.getEngineByName("js");
        mujModel.setRadek2(mujModel.getRadek2() + mujModel.getRadek1());
        try {
            mujModel.setRadek2(
                    js.eval(
                            mujModel.getRadek2()
                    ).toString()
            );
            mujModel.setRadek1("");
            
        } catch (ScriptException ex) {
            System.out.println("Error\n" + ex.getMessage());
        }
    }
    private void zpusob2() {
        if (mujModel.getRadek1().equals("")) return;
        mujModel.setRadek2(mujModel.getRadek2() + mujModel.getRadek1());

        Expression exp = new ExpressionBuilder(mujModel.getRadek2()).build();
        mujModel.setRadek2(Double.toString(exp.evaluate()));
        mujModel.setRadek1("");
    }

}
