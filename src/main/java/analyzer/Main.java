package analyzer;

import java.util.Scanner;

import analyzer.decrypters.BruteForce;
import analyzer.decrypters.StatisticalAnalyzer;
import analyzer.model.Cipher;
import analyzer.utils.FileManager;
import analyzer.utils.Utils;
import analyzer.utils.validators.IValidator;
import analyzer.utils.validators.RussianValidator;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cipher cipher = new Cipher();
        FileManager fileManager = new FileManager();
        IValidator validator = new RussianValidator();

        while (true) {
            displayMenu();
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    encrypt(sc, validator, fileManager, cipher);
                    break;
                case 2:
                    decrypt(sc, validator, fileManager, cipher);
                    break;
                case 3:
                    brudeForce(sc, validator, fileManager);
                    break;
                case 4:
                    staticAnalyze(sc, validator, fileManager);
                    break;
                case 0:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный ввод.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Выберите режим:");
        System.out.println("1. Шифрование");
        System.out.println("2. Расшифровка с ключом");
        System.out.println("3. Brute Force");
        System.out.println("4. Статистический анализ");
        System.out.println("0. Выход");
    }

    private static void encrypt(Scanner sc, IValidator validator, FileManager fileManager, Cipher cipher) {
        System.out.println("Введите путь к файлу для шифрования:");
        String inFile = sc.next();

        String outFile = getOutputFilePath(sc);

        System.out.println("Введите ключ шифрования:");
        int key = sc.nextInt();

        if (!validator.isFileExists(inFile) || !validator.isValidKey(key)) {
            System.out.println("Ошибка валидации входных данных.");
        }

        String result = addNewLine(cipher.encrypt(fileManager.readFile(inFile), key));
        System.out.println("Шифрование завершено.");

        if (null == outFile) {
            System.out.println(result);
            return;
        }

        fileManager.writeFile(result, outFile);
    }

    private static void decrypt(Scanner sc, IValidator validator, FileManager fileManager, Cipher cipher) {
        System.out.println("Введите путь к файлу для шифрования:");
        String inputFile = sc.next();
        String outputFile = getOutputFilePath(sc);
        System.out.println("Введите ключ шифрования:");
        int decryptKey = sc.nextInt();

        if (!validator.isFileExists(inputFile) && !validator.isValidKey(decryptKey)) {
            System.out.println("Ошибка валидации входных данных.");
            return;
        }

        String encryptedText = fileManager.readFile(inputFile);
        String decryptedText = addNewLine(cipher.decrypt(encryptedText, decryptKey));
        fileManager.writeFile(decryptedText, outputFile);
        System.out.println("Расшифровка завершена.");
    }

    private static void brudeForce(Scanner sc, IValidator validator, FileManager fileManager) {
        System.out.println("Введите путь к файлу для brute force:");
        String inputFile = sc.next();
        String outFile = getOutputFilePath(sc);
        boolean isNeedWriteToFile = null != outFile;
        boolean isNeedPrint = false;
        if (isNeedWriteToFile) {
            System.out.println("Нужно выводить каждый результать в консоль? да/нет");
            isNeedPrint = sc.next().equalsIgnoreCase("да");
        }

        if (!validator.isFileExists(inputFile)) {
            System.out.println("Ошибка валидации входных данных.");
            return;
        }

        String encryptedText = fileManager.readFile(inputFile);
        String result = addNewLine(
                new BruteForce().decryptByBruteForce(encryptedText, Utils.ABC.RUSSIAN_ABC, isNeedPrint));
        System.out.println("Расшифровка методом brute force завершена.");

        if (!isNeedWriteToFile) {
            System.out.println(result);
            return;
        }
        fileManager.writeFile(result, outFile);
    }

    private static void staticAnalyze(Scanner sc, IValidator validator, FileManager fileManager) {

        System.out.println("Введите путь к зашифрованному файлу для статистического анализа:");
        String inputFileStatAnalysis = sc.next();
        System.out.println("Введите путь к файлу для анализа на основе языка:");
        String languageFile = sc.next();
        String outputFileStatAnalysis = getOutputFilePath(sc);

        if (!validator.isFileExists(inputFileStatAnalysis) || !validator.isFileExists(languageFile)) {
            System.out.println("Ошибка валидации входных данных.");
            return;
        }

        String encryptedText = fileManager.readFile(inputFileStatAnalysis);
        String languageSampleText = fileManager.readFile(languageFile);
        // FIXME
        String decryptedText = String.valueOf(new StatisticalAnalyzer().findMostLikelyShift(encryptedText,
                Utils.ABC.RUSSIAN_ABC, languageSampleText));

        System.out.println("Расшифровка с помощью статистического анализа завершена.");

        if (null == outputFileStatAnalysis) {
            System.out.println(decryptedText);
            return;
        }

        fileManager.writeFile(decryptedText, outputFileStatAnalysis);
    }

    private static String getOutputFilePath(Scanner sc) {
        System.out.println("Выхотите записать результат в файл? да/нет");
        String answer = sc.next();
        if (answer.equalsIgnoreCase("нет")) {
            return null;
        }

        if (answer.equalsIgnoreCase("да")) {
            System.out.println("Введите путь к выходному файлу:");
            return sc.next();
        }

        System.out.println("Ответ пользователя не был определен.");
        return getOutputFilePath(sc);
    }

    private static String addNewLine(String text) {
        return text.replace("    ", "\n");
    }
}
