<!--/shop-->
<section class="banner-bottom py-lg-5 py-3 ">
    <div class="container">
        <div class="inner-sec-shop pt-lg-4 pt-3">
            <div class="row">
                <div class="col-lg-4 single-right-left ">
                    <div class="grid images_3_of_2">
                        <div class="flexslider1">


                            <div class="thumb-image"> <img src="images/f2.jpg" data-imagezoom="true" class="img-fluid" alt=" "> </div>

                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-8 single-right-left simpleCart_shelfItem">
                    <form  method="post">
                        <h3><c:out value="${requestScope.product.name}"/></h3>
                        <p hidden name="productid" value="${requestScope.product.id}"/>
                        <h4>Product by: <c:out value="${requestScope.product.storeName}"/></h4>
                        <h5>Category: <c:out value="${requestScope.product.categoryName}"/></h5>
                        <div class="rating1">
                            <ul class="stars">

                                <c:choose>
                                    <c:when test="${ requestScope.averageRate eq 0}">
                                        <c:forEach begin="1" end="5">
                                            <i class="fa fa-star fa-2x text-muted" aria-hidden="true"></i>
                                        </c:forEach>

                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach begin="1" end="${requestScope.averageRate}">
                                            <i class="fa fa-star fa-2x text-primary" aria-hidden="true"></i>
                                        </c:forEach>
                                        <c:forEach begin="${(requestScope.averageRate)+1}" end="5">
                                            <i class="fa fa-star fa-2x text-muted" aria-hidden="true"></i>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>




                            </ul>
                        </div>
                        <hr>
                        <p><span>Age:+<c:out value="${requestScope.product.minAge}"/></span>

                        </p>
                        <hr>


                        <c:choose>
                            <c:when test="${ requestScope.product.discountPercentage eq 0}">
                                <p><span class="item_price">$<c:out value="${requestScope.product.price}"/></span></p>
                                <button  hidden="hidden" name="totalcost" value="${requestScope.product.price}"></button>
                            </c:when>
                            <c:otherwise>
                                <p><span class="item_price">$<c:out value="${(requestScope.product.price-(requestScope.product.price*(requestScope.product.discountPercentage/100)))}"/></span></p>
                                <del>$<c:out value="${requestScope.product.price}"/></del>
                                <button  hidden="hidden" name="totalcost" value="${(requestScope.product.price-(requestScope.product.price*(requestScope.product.discountPercentage/100)))}"></button>
                            </c:otherwise>
                        </c:choose>


                        <hr>

                        <div class="description">

                            <div class="form-group">
                                <label>Description</label>
                                <p><span ><c:out value="${requestScope.product.description}"/></span></p>


                            </div>
                        </div>
                        <hr>


                        <div class="form-group row">
                            <label  class="col-sm-2 col-form-label">Quantity</label>
                            <div class="col-sm-2">
                                <select class="form-control" id="selection" name="quantity">

                                    <c:forEach begin="1" end="${requestScope.product.quantity}" varStatus="inner">
                                        <option value="${inner.index}"><c:out value="${inner.index}"/></option>
                                    </c:forEach>
                                </select>
                            </div>

                        </div>



                        <div class="occasion-cart">
                            <div class="toys single-item singlepage">


                                <button type="submit"  name="submitButton" value="cart" formaction="ShoppingCartServlet" class="toys-cart ptoys-cart add">
                                    Add to Cart
                                </button>
                                <button type="submit"  name="submitButton" value="wishes" formaction="ProductHandling" class="toys-cart ptoys-cart ">
                                    Add to wishes
                                </button>
                                <input  name="productid" hidden="hidden" value="${requestScope.product.id}"/>
                            </div>
                        </div>
                    </form>



                </div>
            </div>
        </div>
    </div>

    <!--review-->
    <div class="responsive_tabs">
        <div id="horizontalTab">
            <ul class="resp-tabs-list">



            </ul>
            <div class="resp-tabs-container">
                <div class="tab2">
                    <div class="single_page">
                        <div class="bootstrap-tab-text-grids">
                            <div class="bootstrap-tab-text-grid">


                                <h4>Reviews</h4>
                                <c:choose>
                                    <c:when test="${ requestScope.averageRate eq 0}">
                                        <p>No Reviews</p>
                                        <hr/>
                                    </c:when>
                                    <c:otherwise>

                                        <c:forEach items="${requestScope.reviews}" var="current">


                                            <div class="bootstrap-tab-text-grid-left">
                                                <img src="images/team1.jpg" alt=" " class="img-fluid">
                                            </div>
                                            <div class="bootstrap-tab-text-grid-right">


                                                <ul>
                                                    <li><a href="#"><c:out value="${current.userName}"/></a></li>
                                                    <li>
                                                        <div class="rating1">
                                                            <ul class="stars">
                                                                <c:forEach begin="1" end="${current.rate}">
                                                                    <i class="fa fa-star fa-2x text-primary" aria-hidden="true"></i>
                                                                </c:forEach>
                                                                <c:forEach begin="${(current.rate)+1}" end="5">
                                                                    <i class="fa fa-star fa-2x text-muted" aria-hidden="true"></i>
                                                                </c:forEach>

                                                            </ul>
                                                        </div>
                                                    </li>

                                                </ul>
                                                <div class="container  py-lg-5 py-md-5 py-sm-4 py-4">
                                                    <div class="bootstrap-tab-text-grid">
                                                        <div class="single_page">
                                                            <p class="para"><span><c:out value="${current.reviewDescription}"/></span>
                                                            </p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="clearfix"> </div>
                                            <hr/>


                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>


                            </div>
                            <div class="add-review">
                                <h4>add a review</h4>
                                <form action="ReviewServlet" method="post">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <input type="text" name="userName"  placeholder="Enter your name" required="">
                                        </div>
                                        <div class="col-md-6">
                                            <input type="email" name="userEmail"  placeholder="Enter your email" required="">
                                        </div>
                                        <!--stars-->
                                        <div class="rate" >
                                            <input type="radio" id="star5" name="rate" value="5" />
                                            <label for="star5" title="text">5 stars</label>
                                            <input type="radio" id="star4" name="rate" value="4" />
                                            <label for="star4" title="text">4 stars</label>
                                            <input type="radio" id="star3" name="rate" value="3" />
                                            <label for="star3" title="text">3 stars</label>
                                            <input type="radio" id="star2" name="rate" value="2" />
                                            <label for="star2" title="text">2 stars</label>
                                            <input type="radio" id="star1" name="rate" value="1" />
                                            <label for="star1" title="text">1 star</label>
                                        </div>
                                        <!--end-->
                                    </div>
                                    <textarea name="reviewDescription"   rows="2" cols="3" placeholder="Enter your message" required=""></textarea>
                                    <input type="submit" class="toys-cart ptoys-cart" value="SEND">

                                    <input  name="productid" hidden="hidden" value="${requestScope.product.id}"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>