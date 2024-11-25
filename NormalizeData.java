import java.io.*;
import java.util.*;

public class NormalizeData {
	public static void main(String[] args) throws IOException {
		// 入力ファイルパスと出力ファイルパスを指定
		String inputFilePath = "input.tsv";
		String outputFilePath = "output.tsv";

		// 入力ファイルの読み込み
		BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
		List<String[]> normalizedData = new ArrayList<>();

		String line;
		while ((line = reader.readLine()) != null) {
			// タブで分割して列ごとに処理
			String[] columns = line.split("\t");

			// 列ごとに値を分割して正規化
			List<String[]> normalizedRows = normalizeRow(columns);

			// 正規化されたデータをリストに追加
			normalizedData.addAll(normalizedRows);
		}

		// リソースをクローズ
		reader.close();

		// 正規化されたデータを出力
		writeOutputFile(outputFilePath, normalizedData);

		System.out.println("第一正規化が完了しました。結果は " + outputFilePath + " に保存されました。");
	}

	/**
	 * 行を第1正規化する。
	 * @param columns 行の各列の配列
	 * @return 正規化された行のリスト
	 */
	private static List<String[]> normalizeRow(String[] columns) {
		List<String[]> normalizedRows = new ArrayList<>();

		// セルに複数値が存在する場合、それを展開
		normalizeRecursively(columns, 0, new String[columns.length], normalizedRows);

		return normalizedRows;
	}

	/**
	 * 再帰的に各列を展開して正規化。
	 */
	private static void normalizeRecursively(String[] columns, int colIndex, String[] currentRow, List<String[]> result) {
		if (colIndex == columns.length) {
			// すべての列を処理済みなら行を追加
			result.add(currentRow.clone());
			return;
		}

		// 現在の列を分割（:で値を分ける）
		String[] values = columns[colIndex].split(":");

		// 各値について再帰呼び出し
		for (String value : values) {
			currentRow[colIndex] = value;
			normalizeRecursively(columns, colIndex + 1, currentRow, result);
		}
	}

	/**
	 * 正規化されたデータを出力ファイルに書き込む。
	 *
	 * @param filePath 出力ファイルのパス
	 * @param data 正規化されたデータ
	 * @throws IOException ファイル書き込み時の例外
	 */
	private static void writeOutputFile(String filePath, List<String[]> data) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

		for (String[] row : data) {
			writer.write(String.join("\t", row));
			writer.newLine();
		}

		writer.close();
	}
}
