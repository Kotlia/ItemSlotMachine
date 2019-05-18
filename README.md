What is it?

ItemSlotMachine adds realistic slot machines to your server that have a display which consists of item frames, make sounds when active and can be deactivated either manually or automatically. In order to activate a slot machine you have to hold the coin item in your hand and right click the slot of a slot machine. Everything is highly customisable!




Features


Vault is an optional dependency
You can create and manage own slot machine designs (default design is included)
Template for creating slot machine configs which doesn't have to be reloaded
You can build an infinite number of slot machine
Many commands for managing slot machines
Every slot machine has its own config file
Statistics for slot machines and players which can be displayed normally or as a top ten list
Highly customisable language files which accept all characters
Overall easy usage
All listed features above are always referring to the newest version of ItemSlotMachine!




Upcoming features


Different coins
Support for larger numbers on signs
Commands as combo actions
Allow coins to be added to the pot and to be distributed in combos
Allow the creation of special items (with custom name and/or lore) which can be added to the pot and be distributed in combos
Custom sign layout and more variable (for example <player>)
YOU can contribute to ItemSlotMachine! Just open a new ticket with your suggestion and it may get added to ItemSlotMachine if it's good.




Commands & Permissions


Command	Description	Permission
/design wand	Gives you the design wand	ItemSlotMachine.design.wand
/design create [name]	Creates a new design from your selection	ItemSlotMachine.design.create
/design remove <name>	Removes an existing design	ItemSlotMachine.design.remove
/design list	Shows a list of available designs	ItemSlotMachine.design.list
/design invert <name>	Inverts the item frame loading sequence of a design	ItemSlotMachine.design.invert
/design reload	Reloads all designs	ItemSlotMachine.design.reload
/design help [page]	Shows the help pages for the design command	None
/coin purchase <amount>	Allows you to purchase coins	ItemSlotMachine.coin.purchase
/coin grant <player> <amount>	Grants a player an amount of coins	ItemSlotMachine.coin.grant
/coin help [page]	Shows the help pages for the coin command	None
/slot build <design> [name]	Builds a new slot machine of a design	ItemSlotMachine.slot.build
/slot destruct <name>	Destructs an existing slot machine	ItemSlotMachine.slot.destruct
/slot list	Shows a list of available slot machines	ItemSlotMachine.slot.list
/slot tp <name>	Teleports you to a slot machine	ItemSlotMachine.slot.tp
/slot rebuild <name>	Deactivates and rebuilds an existing slot machine	ItemSlotMachine.slot.rebuild
/slot move <name> <amount>	Moves a slot machine into your looking direction by an amount	ItemSlotMachine.slot.move
/slot deactivate <name>	Deactivates an active slot machine	ItemSlotMachine.slot.deactivate
/slot money <name> <deposit/withdraw/set> <amount>	Modifies the money pot of a slot machine	ItemSlotMachine.slot.money
/slot item <name> <deposit/set> <hand/items>	Modifies the item pot of a slot machine	ItemSlotMachine.slot.item
/slot reset <name> <money/item>	Resets a pot of a slot machine	ItemSlotMachine.slot.reset
/slot clear <name> <money/item>	Clears a pot of a slot machine	ItemSlotMachine.slot.clear
/slot reload [name]	Reloads the whole plugin or a single slot machine	ItemSlotMachine.slot.reload
/slot help [page]	Shows the help pages for the slot command	None
/statistic show <slot/player> <name>	Shows the statistic of a slot machine or a player	ItemSlotMachine.statistic.show
/statistic top <slot/player> <category>	Shows the top ten statistics for a category for slot machines or players	ItemSlotMachine.statistic.top
/statistic reset <slot/player> <name>	Resets the statistic of a slot machine or a player	ItemSlotMachine.statistic.reset
/statistic help [page]	Shows the help pages for the statistic command	None
-	All ItemSlotMachine permissions	ItemSlotMachine.*
-	All design permissions	ItemSlotMachine.design.*
-	All coin permissions	ItemSlotMachine.coin.*
-	All slot machine permissions	ItemSlotMachine.slot.*
-	All slot machine modify permissions	ItemSlotMachine.slot.modify.*
-	All statistic permissions	ItemSlotMachine.statistic.*
-	Check the name of a slot machine by right clicking it	ItemSlotMachine.slot.check
-	Use a slot machine	ItemSlotMachine.slot.use
-	Modify a specific slot machine	ItemSlotMachine.slot.modify.<name>




Config

Take a look at this page for the latest default config!




Coin Shop

Write [CoinShop] in the first line of a sign and click done, now it'll create a coin shop sign automagically! You can change the amount of coins you want to buy by pointing at the sign and scrolling up/down for increasing/decreasing the amount. (if you hold shift while scrolling the amount will increase/decrease by 10) After you've chosen the amount of coins you want to buy right click it.




Wiki
I'm going to set up a wiki on GitHub soon!




Source
The source can be found right here!




Bugs? Errors? Problems? New ideas?
If you find a bug, encounter an error or have new ideas or suggestions for this plugin feel free to create a ticket so we can react to things faster than posting it in the comments! However you can post feedback in the comments, but please use the ticket system for the things I mentioned.


https://www.youtube.com/watch?v=ZKtNvA-XLIE
