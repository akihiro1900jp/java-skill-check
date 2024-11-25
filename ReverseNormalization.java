import java.io.*;
import java.util.*;

public class ReverseNormalization {
	public static void main(String[] args) throws IOException {
		// 入力ファイルパスと出力ファイルパスを指定
		String inputFilePath = "input_reverse.tsv";
		String outputFilePath = "output_reverse.tsv";

		// 入力データを読み込む
		Map<String, List<String>> groupedData = readInputFile(inputFilePath);

		// データをソート
		List<Map.Entry<String, List<String>>> sortedEntries = sortGroupedData(groupedData);

		// ソートされたデータを出力
		writeOutputFile(outputFilePath, sortedEntries);

		System.out.println("逆正規化が完了しました。結果は " + outputFilePath + " に保存されました。");
	}

	/**
	 * 入力ファイルを読み込み、データをグループ化する。
	 *
	 * @param filePath 入力ファイルのパス
	 * @return グループ化されたデータ（キー -> 値のリスト）
	 * @throws IOException ファイル読み込み時の例外
	 */
	private static Map<String, List<String>> readInputFile(String filePath) throws IOException {
		Map<String, List<String>> groupedData = new LinkedHashMap<>();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line;

		while ((line = reader.readLine()) != null) {
			// タブで分割してキーと値を取得
			String[] columns = line.split("\t", -1);

			// 行の形式をチェック
			if (columns.length != 2) {
				System.err.println("Invalid line format: " + line);
				continue;
			}

			String key = columns[0];
			String value = columns[1];

			// マップにキーが存在しない場合、新しいリストを作成
			groupedData.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
		}

		reader.close();
		return groupedData;
	}

	/**
	 * グループ化されたデータをソートする。
	 *
	 * @param groupedData グループ化されたデータ
	 * @return ソートされたエントリのリスト
	 */
	private static List<Map.Entry<String, List<String>>> sortGroupedData(Map<String, List<String>> groupedData) {
		List<Map.Entry<String, List<String>>> sortedEntries = new ArrayList<>(groupedData.entrySet());

		sortedEntries.sort((e1, e2) -> {
			// 空の値を持つキーを優先的に前に移動
			boolean e1HasEmptyValue = e1.getValue().contains("");
			boolean e2HasEmptyValue = e2.getValue().contains("");

			if (e1HasEmptyValue && !e2HasEmptyValue) return -1;
			if (!e1HasEmptyValue && e2HasEmptyValue) return 1;
			return 0; // 順序を維持
		});

		return sortedEntries;
	}

	/**
	 * ソートされたデータを出力ファイルに書き込む。
	 *
	 * @param filePath 出力ファイルのパス
	 * @param sortedEntries ソートされたデータ
	 * @throws IOException ファイル書き込み時の例外
	 */
	private static void writeOutputFile(String filePath, List<Map.Entry<String, List<String>>> sortedEntries) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

		for (Map.Entry<String, List<String>> entry : sortedEntries) {
			String key = entry.getKey();
			String values = String.join(":", entry.getValue());
			writer.write(key + "\t" + values);
			writer.newLine();
		}

		writer.close();
	}
}
