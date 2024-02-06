# Flippy
## What is flippy
Flippy is a simple mod that I am developing to make it easier to flip items on the bazaar, and in the future maybe the auction house.

It's really only a calculator that grabs the prices of items from the Hypixel api.
## How to use Flippy
The command to use flippy is '/flippy {type} {itemId} {amount}'

e.g. /flippy normal SLIME_BALL 10000

At the moment just running '/flippy' opens up a broken GUI, I have plans to fix it in the future

The command will calculate and output the total profit based on the type of flip selected, and the amount of the given item

### Arguments
1. type

Type can either be 'normal' or 'craft'

'normal' is the normal flip where you create a buy order, then make a sell order once it's full

'craft' is a flip where you create buy orders, and craft the items into a new item, then create a sell order above them

if 'craft' is selected, the command will also output the amount of each item that you need to buy

2. itemId

This is the id of the item that you want to craft and/or flip (e.g. "ENCHANTED_GOLD")

3. amount

This is the amount of the item that you want to craft and/or flip
