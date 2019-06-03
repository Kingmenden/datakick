datakick
=====

datakick is a java library for the `Datakick` open product database API.

Check out the `full documentation` or skip to the quick tutorial below.

Usage
=====

Installation:
-------------

    Download .jar file from GitHub here
    https://github.com/Kingmenden/datakick/blob/master/datakick.jar	

    then

    add the file into the External Libraries of your project

Sample Code:
------------
Once downloaded and added to External Libraries
Where you are calling or testing the API add the code

    DatakickApi datakick = new DatakickApi();

Search for a specific product by barcode: 

    String gtin14 = "037000062219";
    DatakickProduct product = datakick.lookupDatakickItem(gtin14);
    System.out.println(product.getBrand_name + " " + product.getName()); }
    >>>'Pro-Health Clean Mint Toothpaste'

Query for products using a search term:

    DatakickProduct[] products = datakick.queryForListOfItems("Toothpaste")
    for(int i = 0; i < products.length; i++) {
    DatakickProduct product = products[i];
    System.out.println(product.getBrand_name + " " + product.getName()); }
    >>>'Sensodyne Fresh Impact Toothpaste'
    >>>'Colgate Complete Protection Sensitive Toothpaste'
    # etc.

Add/modify products in the Datakick database:

    String gtin14 = "037600121149";	
    DatakickProduct product = datakickApi.addDatakickItem(gtin14,
       "name=pepperoni", "brand_name=Hormel", "total_fat=10");

    System.out.println(product.getBrand_name() + " " + product.getName()); }
    >>>'Hormel pepperoni'

You are able to add as many optional parameters after the gtin14 as you want
See the datakick documentation for optional parameters or list below:
https://www.datakick.org/api

List the products (first items available):

    DatakickProduct[] products = datakick.getListOfItems(); # each page returns 100 products

    for(int i = 0; i < products.length; i++) {
    DatakickProduct product = products[i];
    System.out.println(product.getGtin14() + " " + product.getName()); }
    >>>'09780545349215 Wings of Fire - The Dark Secret'
    >>>'09780945564317 The Beginners Bible'
    >>>'00078073004997 Andy Griffiths'
    # etc.

List the products (from a page number):
Will be coming soon

Add an image to a product in the Datakick database:
Will be coming soon

Optional Arguments for Adding/Modifying a product:
--------------------------------------------------

|Optional Arguments   |  Type    |    Units    | Example                |
|---|---|---|---|
|name|                    String|       n/a|        "name=Toothpaste"|
|brand_name|              String|       n/a|        "brand_name=Colgate"|
|size|                    String|       n/a|        "size=20oz"|
|ingredients|             String|       n/a|        "ingredients=Milk, Chocolate, Sugar"|
|serving_size|            String|       n/a|        "serving_size=2 tbsp."|
|servings_per_container|  String|       n/a|        "servings_per_container=2 cookies"|
|calories|                String|       n/a|        "calories=200"|
|fat_calories|            String|       n/a|        "fat_calories=100"|
|fat|                     String|       grams|      "fat=10"|
|saturated_fat|           String|       grams|      "saturated_fat=10"|
|trans_fat|               String|       grams|      "trans_fat=0"|
|polyunsaturated_fat|     String|       grams|      "polyunsaturated_fat=5"|
|monounsaturated_fat|     String|       grams|      "monounsaturated_fat=5"|
|cholesterol|             String|       milligrams| "cholesterol=20"|
|sodium|                  String|       milligrams| "sodium=40"|
|potassium|               String|       milligrams| "potassium=60"|
|carbohydrate|            String|       grams|      "carbohydrates=20"|
|fiber|                   String|       grams|      "fiber=10"|
|sugars|                  String|       grams|      "sugars=6"|
|protein|                 String|       grams|      "protein=4"|
|author|                  String|       n/a|        "author=First M. Last"|
|publisher|               String|       n/a|        "publisher=MyPublisher"|
|pages|                   String|       n/a|        "pages=400"|
|alcohol_by_volume|       String|       percent|    "alcohol_by_volume=20"|

Exceptions:
-----------

- **requests.exceptions.HTTPError** - Will be thrown if the gtin14 provided is invalid or not found in the product database.

Datakick: https://www.datakick.org
full documentation: Will be coming soon
