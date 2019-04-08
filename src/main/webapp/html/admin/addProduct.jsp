

<!-- add Products -->
<section class="checkout py-lg-4 py-md-3 py-sm-3 py-3">
    <div class="container py-lg-5 py-md-4 py-sm-4 py-3">
        <div class="shop_inner_inf">

            <h3>Add <span>Products</span></h3>
            <form method="post" id="addProductForm" action="">


                <div class="form-group row">
                    <label  class="col-sm-2 col-form-label">Category</label>
                    <div class="col-sm-5">
                        <select class="form-control" id="selection" name="categoryName" required>
                            <option>Choose one...</option>
                            <option>Construction</option>
                            <option>Puzzle</option>
                            <option>Dolls</option>
                            <option>Action figures</option>
                            <option>Vehicles</option>
                        </select>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="inputStore" class="col-sm-2 col-form-label">Store</label>
                    <div class="col-sm-5">
                        <input  name="storeName" class="form-control" id="inputStore" placeholder="Store...." required=""/>
                    </div>
                </div>


                <div class="form-group row">
                    <label for="inputName" class="col-sm-2 col-form-label">Product Name</label>
                    <div class="col-sm-5">
                        <input  name="name" class="form-control" id="inputName" placeholder="Product Name...." required=""/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputDescription" class="col-sm-2 col-form-label">Description</label>
                    <div class="col-sm-5">
                        <textarea class="form-control" rows="5" name="description" id="inputDescription" placeholder="Description...." required=""></textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputQuantity" class="col-sm-2 col-form-label">Quantity</label>
                    <div class="col-sm-5">
                        <input  name="quantity" class="form-control" id="inputQuantity" placeholder="Quantity...." required=""/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="inputMinage" class="col-sm-2 col-form-label">Minimum Age</label>
                    <div class="col-sm-5">
                        <input  name="minAge" class="form-control" id="inputMinage" placeholder="Minimum Age...." required=""/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputPrice" class="col-sm-2 col-form-label">Product Price</label>
                    <div class="col-sm-5">
                        <input  name="price" class="form-control" id="inputPrice" placeholder="Product Price...." required=""/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="inputDiscount" class="col-sm-2 col-form-label">Discount</label>
                    <div class="col-sm-5">
                        <input  name="discountPercentage" class="form-control" id="inputDiscount" placeholder="Enter Discount...." required=""/>
                    </div>
                </div>
                <br>

                <!--image-->

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Product Image</label>
                    <div class="col-sm-5">

                        <input type="file" accept="image/png, image/jpeg, image/gif" id="imgInp" name="image" class="btn  btn-file form-control" required="">

                    </div>
                </div>

                <div class=" container col-sm-5">
                    <img id='img-upload'/>
                    <br>

                    <!--HiddenParameter To Validate User Is Try To Register!-->
                    <input name="addproductButton" hidden="hidden" value="true">
                    <div class="float-right">
                        <button type="submit" class="btn btn-primary btn-lg" >Add</button>
                    </div>
                </div>





            </form>
        </div>
    </div>
</section>
