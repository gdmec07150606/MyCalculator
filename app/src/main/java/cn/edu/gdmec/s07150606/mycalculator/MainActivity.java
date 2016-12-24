package cn.edu.gdmec.s07150606.mycalculator;

        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    //计算按钮
    private Button calculatorButton;
    //体重输入框
    private EditText weightEditText;
    //男性选择框
    private RadioButton manCheckBox;
    //女性选择框
    private RadioButton womanCheckBox;
    //显示结果
    private TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private void registerEvent() {
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!weightEditText.getText().toString().trim().equals("")){
                    if (manCheckBox.isChecked() || womanCheckBox.isChecked()) {
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuilder sb = new StringBuilder();
                        sb.append("-------评估结果-----\n");
                        if (manCheckBox.isChecked()) {
                            sb.append("男性标准身高：");
                            double result = evaluateHeight(weight, "男");
                            sb.append((int) result + "(厘米)");
                        }
                        if (womanCheckBox.isChecked()) {
                            sb.append("女性标准身高：");
                            double result = evaluateHeight(weight, "女");
                            sb.append((int) result + "(厘米)");
                        }

                        resultTextView.setText(sb.toString());

                    } else {
                        showMessage("请选择性别！");
                    }


                }else{

                    showMessage("请选择体重！");
                }
            }
        });

    }

    private double evaluateHeight(double weight, String sex) {
        double height;
        if (sex == "男") {
            height = 170 - (62 - weight) / 0.6;
        } else {
            height = 158 - (52 - weight) / 0.5;
        }
        return height;

    }

    private void showMessage(String s) {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统消息");
        alert.setMessage(s);
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}




