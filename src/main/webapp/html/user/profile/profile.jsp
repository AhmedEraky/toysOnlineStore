
 <!--User Profile-->
      <section class="banner-bottom py-lg-5 py-3">
        <div class="container">
			<form>
		<center>
			<div class = "row">
				<div class = "col-5">
					<img src="${requestScope.user.imgPath}" class="rounded-circle" alt="User Photo" width="236" height="304">
				</div>
				<center>
                <div>
					<h3 class="typo-main-heading mb-lg-4 mb-3 pr-3 pb-1" id="userName" name="userName">${requestScope.user.userName}</h3>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Birth Date</label>
						<div class="col-sm-8 px-4">
							<input type="text" value="${requestScope.user.birthDate}" name="birthDate" class="form-control" id="birthDate" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Address</label>
						<div class="col-sm-10 px-4">
							<input type="text" value="${requestScope.user.address}" name="address" class="form-control" id="address" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Job</label>
						<div class="col-sm-10 px-4">
							<input type="text" value="${requestScope.user.job}" name="job" class="form-control" id="job" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">Email</label>
						<div class="col-sm-10 px-4">
							<input type="text" value="${requestScope.user.email}" name="email" class="form-control" id="email" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-4 col-form-label">Credit Limit</label>
						<div class="col-sm-8 px-4">
							<input type="number" value="${requestScope.user.creditLimit}" name="credit" class="form-control" id="credit" readonly>
						</div>
					</div>
					<div class="form-group row">
                    <div class="col-sm-10">
                        <button id="edit" class="btn btn-primary">Edit Profile</button>
                    </div>
                  </div>
				</div>
				</center>
				</center>
			</div>
		  </form>
		</div>               
      </section>
<!--User Profile-->