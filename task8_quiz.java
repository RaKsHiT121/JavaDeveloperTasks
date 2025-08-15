import java.util.*;

class Question {
    String questionText;
    String[] options;
    int correctOption; // 1-based index

    Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    boolean ask(Scanner sc) {
        System.out.println("\n" + questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Your answer: ");
        int ans;
        try {
            ans = sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine(); // clear buffer
            return false;
        }
        return ans == correctOption;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Which language runs in a web browser?",
                new String[]{"Java", "C", "Python", "JavaScript"}, 4));
        questions.add(new Question("What does JDBC stand for?",
                new String[]{"Java Database Connectivity", "Java Development Code", "Joint Database Compiler", "None of these"}, 1));
        questions.add(new Question("Who developed Java?",
                new String[]{"Microsoft", "Sun Microsystems", "Google", "Apple"}, 2));

        Scanner sc = new Scanner(System.in);
        int score = 0;

        System.out.println("=== Welcome to the Online Quiz ===");
        for (Question q : questions) {
            if (q.ask(sc)) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong!");
            }
        }

        System.out.println("\n=== Quiz Finished ===");
        System.out.println("Your Score: " + score + "/" + questions.size());

        if (score == questions.size()) {
            System.out.println("🏆 Excellent!");
        } else if (score >= questions.size() / 2) {
            System.out.println("👍 Good job!");
        } else {
            System.out.println("📚 Keep practicing!");
        }

        sc.close();
    }
}
