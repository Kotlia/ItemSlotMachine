<p>Plugin originally written and coded by DarkBlade12, updated to Spigot / Paper 1.16.1 by Kotlia.</p>
<h3> 1.16.1対応日本語版</h3>
<p> by Kotlia (Twitter: @Kotlia_)</p>
</p>
<p></p>
<h3>What is it?</h3>
<p>
ItemSlotMachine は Minecraft Server 内にリアルなスロットマシンを追加します。音とエフェクトで大興奮間違い無し。コインを持って右クリックでスロット開始。ほとんど全ての要素は編集可能です。
</p>
<h3>機能</h3>
<ul>
	<li>Vault 対応。</li>
	<li>オリジナルデザインを作成することが可能です</li>
	<li>Reload無しで新デザインを追加出来ます</li>
	<li>デザインの数に制限はありません</li>
	<li>スロットをゲーム内から直接コマンドで編集可能</li>
	<li>統計情報を表示することが出来ます</li>
	<li>ランキング表示対応</li>
	<li>言語を簡単に追加できます</li>
	<li>ユーザーフレンドリーなインターフェース</li>
	<li>上記の機能は全て最新版の物となります</li>
</ul>
<h3>アップデート予定</h3>
<ul>
	<li>複数通貨</li>
	<li>看板の数の上限上昇</li>
	<li>コマンドの追加</li>
	<li>ジャックポット機能のアップデート</li>
	<li>NBTタグ対応</li>
	<li>カスタム看板 (ex: <player>)</li>
	<li>ご意見・ご要望随時募集中！</li>
</ul>
<h3>コマンド & 権限</h3>
<p>

| コマンド                                           | 説明                                                               | 権限                               |
| -------------------------------------------------- | ------------------------------------------------------------------ | ---------------------------------- |
| /design wand                                       | デザインツールをゲット                                             | ItemSlotMachine.design.wand        |
| /design create <name>                              | 新しいデザインを作成                                               | ItemSlotMachine.design.create      |
| /design remove <name>                              | 既存のデザインを削除                                               | ItemSlotMachine.design.remove      |
| /design list                                       | 既存のデザイン一覧を表示                                           | ItemSlotMachine.design.list        |
| /design invert <name>                              | スロットの目が決まる順番を反転                                     | ItemSlotMachine.design.invert      |
| /design reload                                     | 全てのデザインをリロード                                           | ItemSlotMachine.design.reload      |
| /design help <page>                                | デザインヘルプページを表示                                         | -                                  |
| /coin purchase <amount>                            | コインを購入                                                       | ItemSlotMachine.coin.purchase      |
| /coin grant <player> <amount>                      | コインを渡す                                                       | ItemSlotMachine.coin.grant         |
| /coin help [page]                                  | コインヘルプページを表示                                           | -                                  |
| /slot build <design> [name]                        | 新しいスロットを作成                                               | ItemSlotMachine.slot.build         |
| /slot destruct <name>                              | スロットを削除                                                     | ItemSlotMachine.slot.destruct      |
| /slot list                                         | 既存のスロット一覧を表示                                           | ItemSlotMachine.slot.list          |
| /slot tp <name>                                    | スロットマシンにテレポート                                         | ItemSlotMachine.slot.tp            |
| /slot rebuild <name>                               | スロットマシンを再生成                                             | ItemSlotMachine.slot.rebuild       |
| /slot move <name> <amount>                         | スロットマシンを自分が向いている方向に <amount> ブロック 移動      | ItemSlotMachine.slot.move          |
| /slot deactivate <name>                            | スロットマシンを無効化                                             | ItemSlotMachine.slot.deactivate    |
| /slot money <name> <deposit/withdraw/set> <amount> | ジャックポット内の賞金を操作                                       | ItemSlotMachine.slot.money         |
| /slot item <name> <deposit/set> <hand/items>       | ジャックポットアイテムを操作                                       | ItemSlotMachine.slot.item          |
| /slot reset <name> <money/item>                    | ジャックポットを初期化                                             | ItemSlotMachine.slot.reset         |
| /slot clear <name> <money/item>                    | ジャックポットを削除                                               | ItemSlotMachine.slot.clear         |
| /slot reload <name>                                | <name> のスロットをリロード / 指定がない場合はプラグインをリロード | ItemSlotMachine.slot.reload        |
| /slot help <page>                                  | スロットヘルプページを表示                                         | -                                  |
| /statistic show <slot/player> <name>               | スロット・プレイヤーの統計情報を表示                               | ItemSlotMachine.statistic.show     |
| /statistic top <slot/player> <category>            | 上位 10 名の統計情報を表示                                         | ItemSlotMachine.statistic.top      |
| /statistic reset <slot/player> <name>              | 統計情報をリセット                                                 | ItemSlotMachine.statistic.reset    |
| /statistic help <page>                             | 統計情報ヘルプページを表示                                         | -                                  |
| -                                                  | 全ての ItemSlotMachine 権限                                        | ItemSlotMachine.\*                 |
| -                                                  | 全ての デザイン 権限                                               | ItemSlotMachine.design.\*          |
| -                                                  | 全ての コイン 権限                                                 | ItemSlotMachine.coin.\*            |
| -                                                  | 全ての スロット作成 権限                                           | ItemSlotMachine.slot.\*            |
| -                                                  | 全ての スロット編集 権限                                           | ItemSlotMachine.slot.modify.\*     |
| -                                                  | 全ての 統計情報アクセス 権限                                       | ItemSlotMachine.statistic.\*       |
| -                                                  | 右クリックでスロット情報を確認                                     | ItemSlotMachine.slot.check         |
| -                                                  | スロットマシン使用権限                                             | ItemSlotMachine.slot.use           |
| -                                                  | スロットマシン編集権限（名前指定）                                 | ItemSlotMachine.slot.modify.<name> |

</p>
<h3>Config</h3>
<p>
	ファイル内のConfigは英語のままです。YouTubeの動画（下記・英語）を参照、もしくは Twitter: @Kotlia_ までお問い合わせ下さい。
</p>
<h3>Coin Shop</h3>
<p>
	[coinshop] と看板の一行目に書くことで自動的にコインショップを作成します。ホットバー切り替え（デフォルト：マウススクロール）で購入する量を編集出来ます。スニーク（デフォルト：Shift）を押しながら同じ動作をすることで、10づつの変更が出来ます。右クリックでコインの購入は完了です。Vaultを導入している場合は、自動的に値段分が徴収されます。
</p>

<h3>バグ？エラー？問題？要望？</h3>
<p>
	本家の作成者様へお問い合わせ下さい。
</p>
<p>
公式動画（英語）: https://www.youtube.com/watch?v=ZKtNvA-XLIE
</p>
