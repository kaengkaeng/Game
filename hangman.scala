import scala.io.StdIn
import scala.util.Random

class HangmanGame(wordList: List[String]):
 //"private" 접근 제어자로, 해당 변수가 속한 클래스 내부에서만 접근
  private var choice: String = ""
  private var display: List[Char] = List()
  private var endOfGame: Boolean = false
  private var lives: Int = 6

  // 선택된 단어 선택 및 게임 초기화
  def initializeGame(): Unit = {
    choice = wordList(Random.nextInt(wordList.length))
    println(choice)
   // display 리스트 길이만큼 '_'를 쓴다 
    display = List.fill(choice.length)('_')
    endOfGame = false
    lives = 6 // 6 times try :)
    //println(choice) // 선택된 단어 출력 (테스트용)
    println("Start  Game.Chek word of length : " + display.mkString(" "))
  }

  // 사용자 입력 받기
  def getUserInput(): Char = {
    StdIn.readLine(" Guess a letter : ").toLowerCase.head
  }

  // 추측한 문자 확인하고 보여주기
  def checkGuess(guess: Char): Unit = {
    var correctGuess = false
    for (i <- choice.indices) {
      val letter = choice(i) // letter 변수와 인덱스 i의 value가 같다. 
      if (letter == guess) {
        display = display.updated(i, letter) // i 번째 인덱스에 value(=letter) 추가
        correctGuess = true
      }
    }
    if (!correctGuess) {
      lives -= 1
      println(s"입력한 문자 '$guess'가 단어에 없습니다. 목숨이 줄어듭니다.")
      if (lives == 0) {
        endOfGame = true
        println("Game End: You lose")
      }
    }
    println(display.mkString(" "))
  }

  // 게임 종료 확인
  def checkGameEnd(): Boolean = {
    if (display.contains('_')) {
      false
    } else {
      endOfGame = true
      println("Game End: You win!!")
      true
    }
  }

  // 게임 진행
  def playGame(): Unit = {
    while (!endOfGame) {
      val guess = getUserInput()
      checkGuess(guess)
      if (!checkGameEnd()) {
        println(s"남은 목숨: $lives")
      }
    }
  }


def main(): Unit = {
  val wordList = List("hallo", "sun", "himmel","brezel", "wurst")
  val hangmanGame = new HangmanGame(wordList)
  hangmanGame.initializeGame()
  hangmanGame.playGame()
  }
