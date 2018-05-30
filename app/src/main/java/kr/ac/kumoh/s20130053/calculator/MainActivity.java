package kr.ac.kumoh.s20130053.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView value;
    Button button_0;
    Button button_1;
    Button button_2;
    Button button_3;
    Button button_4;
    Button button_5;
    Button button_6;
    Button button_7;
    Button button_8;
    Button button_9;
    Button button_ac;

    Button button_plus;
    Button button_minus;
    Button button_mul;
    Button button_div;
    Button button_equal;
    Button button_comple;
    Button button_percent;
    Button button_dot;

    // 이벤트 처리를 위해 현재 눌러진 키의 객체를 임시 저장하는 변수
    Button button_CurrentClick;

    // 연산을 위한 벡터 선언
    ArrayList<Double> array = new ArrayList<>();
    static final double PLU = 7.8912;
    static final double MIN = 7.8913;
    static final double MUL = 7.8914;
    static final double DIV = 7.8915;

    // 누른 버튼 값을 이어붙이지 않고 새로운 값을 화면에 출력해야 하는지 판단하는 Flag
    boolean is_newValue = true;

    // 가로모드, 세로모드 변환 시 값을 저정하는 데 사용되는 Map 의 Key 값 선언
    public static final String SavedKey = "Key";

    // Long 타입 변수의 임계값 설정
    public static final long LONG_LIMIT = 9223372036854775807L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 각 버튼 객체 획득 */
        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_ac = findViewById(R.id.button_ac);

        button_plus = findViewById(R.id.button_plus);
        button_minus = findViewById(R.id.button_minus);
        button_mul = findViewById(R.id.button_multiplication);
        button_div = findViewById(R.id.button_divide);
        button_equal = findViewById(R.id.button_equal);
        button_comple = findViewById(R.id.button_complement);
        button_percent = findViewById(R.id.button_percent);
        button_dot = findViewById(R.id.button_dot);

        /* 화면에 값을 표시하는 TextView 객체 획득 */
        value = findViewById(R.id.value);

        /* 각 버튼 객체마다 리스너 부착 */
        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_ac.setOnClickListener(this);

        button_plus.setOnClickListener(this);
        button_minus.setOnClickListener(this);
        button_mul.setOnClickListener(this);
        button_div.setOnClickListener(this);
        button_equal.setOnClickListener(this);
        button_comple.setOnClickListener(this);
        button_percent.setOnClickListener(this);
        button_dot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        button_CurrentClick = findViewById(view.getId()); // 임시로 Button View 저장하는 변수.
        double temp_double;
        long temp_long;

        if (view.getId() == R.id.button_0) { // 0 버튼을 눌렀을 때
            if (!value.getText().toString().equals(button_CurrentClick.getText()) && !is_newValue)
                value.append(button_CurrentClick.getText());
            else
                value.setText(button_CurrentClick.getText());
        } else if (view.getId() == R.id.button_1 // 1~9 버튼을 눌렀을 때
                || view.getId() == R.id.button_2
                || view.getId() == R.id.button_3
                || view.getId() == R.id.button_4
                || view.getId() == R.id.button_5
                || view.getId() == R.id.button_6
                || view.getId() == R.id.button_7
                || view.getId() == R.id.button_8
                || view.getId() == R.id.button_9) {
            if (is_newValue) {
                value.setText(button_CurrentClick.getText()); // 기존값 지우고 새로운 수 출력
                is_newValue = false;
            } else {
                TextResize();
                value.append(button_CurrentClick.getText()); // 입력된 수 옆에 새로운 수 붙이기
            }
        } else if (view.getId() == R.id.button_ac) { // AC 버튼을 눌렀을 때
            value.setText("0");
            array.clear();
            TextResize();
            is_newValue = true;
        } else if (view.getId() == R.id.button_plus) { // ＋ 버튼을 눌렀을 때
            if (Ignore_DoubleClick(PLU))
                return;
            array.add(Double.parseDouble(value.getText().toString()));
            array.add(PLU);
            is_newValue = true;
        } else if (view.getId() == R.id.button_minus) { // － 버튼을 눌렀을 때
            if (Ignore_DoubleClick(MIN))
                return;
            array.add(Double.parseDouble(value.getText().toString()));
            array.add(MIN);
            is_newValue = true;
        } else if (view.getId() == R.id.button_multiplication) { // × 버튼을 눌렀을 때
            if (Ignore_DoubleClick(MUL))
                return;
            array.add(Double.parseDouble(value.getText().toString()));
            array.add(MUL);
            is_newValue = true;
        } else if (view.getId() == R.id.button_divide) { // ÷ 버튼을 눌렀을 때
            if (Ignore_DoubleClick(DIV))
                return;
            array.add(Double.parseDouble(value.getText().toString()));
            array.add(DIV);
            is_newValue = true;
        } else if (view.getId() == R.id.button_complement) { // +/- 버튼을 눌렀을 때
            if (value.getText().toString().substring(0, 1).equals("-"))
                value.setText(value.getText().toString().substring(1, value.getText().toString().length()));
            else
                value.setText("-" + value.getText().toString());
        } else if (view.getId() == R.id.button_percent) { // % 버튼을 눌렀을 때
            temp_double = Double.parseDouble(value.getText().toString()) * 0.01;
            value.setText(String.format("%s", temp_double));
            TextResize();
        } else if (view.getId() == R.id.button_dot) { // . 버튼을 눌렀을 때
            if (!value.getText().toString().contains("."))
                value.setText(value.getText().toString().concat(".")); // .(dot) 삽입 연산
            else {
                if (value.getText().toString().replace(".", "").length() > 19) // Long 변수 임계값 넘어서는 길이에 대한 예외처리
                    temp_long = LONG_LIMIT;
                else
                    temp_long = Long.parseLong(value.getText().toString().replace(".", "")); // .(dot) 제거 연산
                value.setText(String.format("%s", temp_long));
                // ex) 만약 0.67 에서 . 을 없앴다가 만들게 되면 67 로 보정됨
            }
            is_newValue = false;
        } else if (view.getId() == R.id.button_equal) { // ＝ 버튼을 눌렀을 때
            array.add(Double.parseDouble(value.getText().toString()));
            temp_double = Calculator(array);
            if (temp_double % 1 == 0) {
                // 결과값이 정수라면 실수를 정수로 캐스팅
                temp_long = (long) temp_double;
                value.setText(String.format("%s", temp_long));
            } else {
                // 결과값이 실수라면 소수점 2자리까지만 출력
                temp_double = Double.parseDouble(String.format("%.2f", temp_double));
                value.setText(String.format("%s", temp_double));
            }
            is_newValue = true;
            array.clear();
            TextResize();
        }
    }

    public boolean Ignore_DoubleClick(double clicked_button) {
        // 동일한 버튼 2번 누를 시 처리해주는 함수
        // 이 부분 수정해야함..(일단 보류★)
        return !array.isEmpty() && (array.get(array.size() - 2) == Double.parseDouble(value.getText().toString())) && (array.get(array.size() - 1) == clicked_button);
    }

    public Double Calculator(ArrayList<Double> array) {
        for (int j = 0; j < 30; j++) {
            // 동일한 연산자는 30번까지 반복되어 나올 수 있음
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i).equals(MUL)) {
                    array.set(i, array.get(i - 1) * array.get(i + 1));
                    array.remove(i + 1);
                    array.remove(i - 1);
                }
            }

            for (int i = 0; i < array.size(); i++) {
                if (array.get(i).equals(DIV)) {
                    array.set(i, array.get(i - 1) / array.get(i + 1));
                    array.remove(i + 1);
                    array.remove(i - 1);
                }
            }

            for (int i = 0; i < array.size(); i++) {
                if (array.get(i).equals(PLU)) {
                    array.set(i, array.get(i - 1) + array.get(i + 1));
                    array.remove(i + 1);
                    array.remove(i - 1);
                }
            }

            for (int i = 0; i < array.size(); i++) {
                if (array.get(i).equals(MIN)) {
                    array.set(i, array.get(i - 1) - array.get(i + 1));
                    array.remove(i + 1);
                    array.remove(i - 1);
                }
            }
        }
        return array.get(0);
    }

    public void TextResize(){
        // 유동적으로 폰트 사이즈 변경해주는 메소드
        if(value.getText().toString().length() < 8)
            value.setTextSize(75);
        else if(value.getText().toString().length() < 12)
            value.setTextSize(50);
        else
            value.setTextSize(35);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState); // 상태 저장
        outState.putString(SavedKey, value.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState); // 상태 복구
        value.setText(savedInstanceState.getString(SavedKey));
        TextResize();
    }

}

