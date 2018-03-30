<!doctype html>
<html lang="en">
<#include "head.ftl">
</html>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <form role="form" method="post" action="/sell/seller/login">
                <div class="form-group">

                    <label for="exampleInputUsername">
                        Username
                    </label>
                    <input type="text" name="username" class="form-control" id="exampleInputUsername" />
                </div>
                <div class="form-group">

                    <label for="exampleInputPassword">
                        Password
                    </label>
                    <input type="password" name="password" class="form-control" id="exampleInputPassword" />
                </div>
                <button type="submit" class="btn btn-primary">
                    Submit
                </button>
            </form>
        </div>
    </div>
</div>
</body>
