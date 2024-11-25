"# java-skill-check" 

以下に各ファイルの説明を記載します。

課題１．第一正規化

	1. input.tsv
		説明: 
		  - 正規化処理の入力データを含むファイル。
		  - タブ区切りのデータが含まれ、1つのセルに複数の値が含まれることがあります（コロン : で区切られる）。

	2. NormalizeData.java
		説明:
		  - 第一正規化（1つのセルに複数値が含まれるデータを1セル1値に展開）を行うJavaプログラム。
		  - 入力ファイル（input.tsv）を読み取り、正規化された結果を出力ファイル（output.tsv）に保存。

	3. run_normalize.bat
		説明:
		  - 正規化処理（NormalizeData.java）を簡単に実行するためのバッチファイル。
		  - 実行時に、input.tsv を処理し、output.tsv を生成します。
		使用方法:
		  - ファイルをダブルクリック、またはコマンドラインで以下を実行:
		    run_normalize.bat

課題２．第一正規化の逆変換

	4. input_reverse.tsv
		説明:
		  - 逆正規化処理の入力データを含むファイル。
		  - タブ区切りのデータが含まれ、1列目がキー、2列目が値を表します。
		    
	5. ReverseNormalization.java
		説明:
		  - データの逆正規化（同じキーを持つ値をグループ化し、コロン : で連結）を行うJavaプログラム。
		  - 入力ファイル（input_reverse.tsv）を読み取り、逆正規化された結果を出力ファイル（output_reverse.tsv）に保存。
	    
	6. run_reverse.bat
		説明:
		  - 逆正規化処理（ReverseNormalization.java）を簡単に実行するためのバッチファイル。
		  - 実行時に、input_reverse.tsv を処理し、output_reverse.tsv を生成します。
		使用方法:
		  - ファイルをダブルクリック、またはコマンドラインで以下を実行:
		    run_reverse.bat