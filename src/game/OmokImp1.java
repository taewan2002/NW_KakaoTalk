package game;

public class OmokImp1 {
    public class OmokImpl implements OmokInterface {
        int[][] omok = new int[10][10];
        // 오목판 크기 x, y 전체 좌표

        @Override
        // 오목판 출력 함수
        public void viewOmok() {
            int numY = 0;
            // 오목 좌측 좌표값 초기화

            int numX = 64;
            // 오목 하단 좌표값 초기화

            for(int[] i : omok) {
                numY++;

                System.out.print(numY < 10 ? numY + " " : numY);
                // 좌측에 Y 좌표 표시
                // 조건문을 넣어 10 이하일 경우 공백을 넣은 이유는
                // 오목판을 보기 좋게 꾸며주기 위해서 입니다.

                for(int j : i) {
                    if(j == 1) {
                        System.out.print(" ●");
                        // 흑돌 표시
                    } else if(j == 2) {
                        System.out.print(" ○");
                        // 백돌 표시
                    } else {
                        System.out.print(" ㆍ");
                        // 공백 표시
                    }
                }
                System.out.print("\n");
            }

            System.out.print("  ");

            for(int[] i : omok) {
                numX++;
                System.out.print(" " + (char)numX);
                // 하단에 X축 좌표 표시
                // X축 또한 오목판을 보기 좋게 꾸미기 위해서 공백을 추가했습니다.
            }

            System.out.print("\n");
        }

        @Override
        // 오목
        public int OmokAction(char x, int y, int turn) {
            int chX = ((int) x) - 97;
            // x축으로 입받은 아스키코드 숫자로 변경

            int chY = --y;
            // 배열에 넣는 값 -1

            // 오목돌 체크
            int count = 0;

            int _x,_y = 0;

            String name = new String();
            name = (turn == 1) ? "흑돌" : "백돌";

            if( omok[chY][chX] != 0 ) {
                System.out.println("이미 오목돌이 설치되어 있습니다.");
                return 0;
            } else {
                omok[chY][chX] = turn;
            }

            // 가로 체크 코드
            _x = chX;
            _y = chY;
            count = 0;
            while(omok[_y][_x] == turn && _x > 0) {
                _x--;
            }
            while(omok[_y][++_x] == turn && _x <= 10) {
                count++;
                // System.out.println(count+"오목체크");
            }
            if(count == 5) {
                System.out.println(name + "승리");
                return 1;
            }

            // 세로 체크 코드
            _x = chX;
            _y = chY;
            count = 0;
            while(omok[_y][_x] == turn && _y > 0) {
                _y--;
            }
            while(omok[++_y][_x] == turn && _y <= 10) {
                count++;
                // System.out.println(count+"오목체크");
            }
            if(count == 5) {
                System.out.println(name + "승리");
                return 1;
            }

            // 대각선 ↘
            _x = chX;
            _y = chY;
            count = 0;
            while(omok[_y][_x] == turn && _y > 0 && _x > 0) {
                _y--;
                _x--;
            }
            while(omok[++_y][++_x] == turn && _y <= 10 && _x <= 10) {
                count++;
                // System.out.println(count+"오목체크");
            }
            if(count == 5) {
                System.out.println(name + "승리");
                return 1;
            }

            // 대각선 ↗
            _x = chX;
            _y = chY;
            count = 0;
            while(omok[_y][_x] == turn && _y > 0 && _x > 0) {
                _y++;
                _x--;
            }
            while(omok[--_y < 0 ? 0 : _y][++_x] == turn && _y <= 10 && _x <= 10) {
                // omok[][] 에서 y 값에 조건문을 넣은 이유는 a, 1 을 입력시
                // y 값이 -1 로 음수값으로 넘어가면 오류를 출력해서 조정해 주었습니다.
                count++;
            }
            if(count == 5) {
                System.out.println(name + "승리");
                return 1;
            }

            return 0;
        }
}
