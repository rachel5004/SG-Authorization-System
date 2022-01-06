import React, { useState, useRef } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, Redirect } from "react-router-dom";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";

import { register } from "../actions/auth";
import AuthService from "../services/auth.service";

const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const validEmail = (value) => {
  if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
  }
};

const vusername = (value) => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="alert alert-danger" role="alert">
        The username must be between 3 and 20 characters.
      </div>
    );
  }
};

// const vpassword = (value) => {
//   if (value.length < 6 || value.length > 40) {
//     return (
//       <div className="alert alert-danger" role="alert">
//         The password must be between 6 and 40 characters.
//       </div>
//     );
//   }
// };
const verifyCode = (value) => {
  if (value.length < 6 || value.length > 40) {
    return (
      <div className="alert alert-danger" role="alert">
        The password must be between 6 and 40 characters.
      </div>
    );
  }
};

const Register = () => {
  const form = useRef();
  const checkBtn = useRef();

  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [code, setCode] = useState("");
  const [password, setPassword] = useState("");
  const [successful, setSuccessful] = useState(false);

  const { message } = useSelector((state) => state.message);
  const dispatch = useDispatch();

  const onChangeUsername = (e) => {
    const username = e.target.value;
    setUsername(username);
  };

  const onChangeEmail = (e) => {
    const email = e.target.value;
    setEmail(email);
  };

  const onChangePassword = (e) => {
    const password = e.target.value;
    setPassword(password);
  };

  const sendEmailRequest = (e) => {
    e.preventDefault();
    console.log("send");
    const verify = document.querySelector(".emailverify");
    verify.classList.remove("emailverify");
    AuthService.sendEmail(email)
      .then((response) => {
        console.log(response);
        setCode(response);
      })
      .catch((error) => {
        console.log(error);
      });
    console.log(code);
  };

  const verifyCode = (e) => {
    e.preventDefault();
    const inputCode = document.querySelector(".code").value;
    if (inputCode.length < 1) {
      console.log("please input code");
    }
    if (inputCode === String(code)) {
      const verify = document.querySelector(".isVerified");
      verify.innerText = "인증 완료";
    }
  };

  const handleRegister = (e) => {
    e.preventDefault();

    setSuccessful(false);

    form.current.validateAll();

    if (checkBtn.current.context._errors.length === 0) {
      dispatch(register(username, email, password))
        .then((response) => {
          setSuccessful(true);
          console.log("success resi");
          if (response.status === 201) {
            // 회원가입 성공, 모달로 팝업 후 로그인으로 redirect
            return <Redirect to="/login" />;
          } else if (response.status === 200) {
            // 이미 존재하는 id
            console.log("already exist");
            // return <Redirect to="/login" />;
          }
        })
        .catch((error) => {
          console.log(error);
          setSuccessful(false);
        });
    }
  };

  return (
    <div className="col-md-12">
      <div className="card card-container">
        <img
          src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
          alt="profile-img"
          className="profile-img-card"
        />

        <Form onSubmit={handleRegister} ref={form}>
          {!successful && (
            <div>
              <div className="form-group">
                <label htmlFor="username">Username</label>
                <Input
                  type="text"
                  className="form-control"
                  name="username"
                  value={username}
                  onChange={onChangeUsername}
                  validations={[required, vusername]}
                />
              </div>

              <div className="form-group">
                <label htmlFor="email">Email</label>
                <Input
                  type="text"
                  className="form-control"
                  name="email"
                  value={email}
                  onChange={onChangeEmail}
                  validations={[required, validEmail]}
                />
                <button
                  className="btn btn-primary btn-block send-email"
                  onClick={sendEmailRequest}
                >
                  인증 요청
                </button>
              </div>

              <div className="form-group emailverify">
                <label htmlFor="code">Verify Code</label>
                <Input
                  type="text"
                  className="form-control code"
                  name="code"
                  validations={[required]}
                />
                <button
                  className="btn btn-primary btn-block isVerified"
                  onClick={verifyCode}
                >
                  확인
                </button>
              </div>

              <div className="form-group">
                <label htmlFor="password">Password</label>
                <Input
                  type="password"
                  className="form-control"
                  name="password"
                  value={password}
                  onChange={onChangePassword}
                  validations={[required]}
                />
              </div>

              <div className="form-group">
                <button className="btn btn-primary btn-block">Sign Up</button>
              </div>
            </div>
          )}

          {message && (
            <div className="form-group">
              <div
                className={
                  successful ? "alert alert-success" : "alert alert-danger"
                }
                role="alert"
              >
                {message}
              </div>
              <div className="form-group">
                <Link to={"/login"}>
                  <button className="btn btn-primary btn-block">Log In</button>
                </Link>
              </div>
            </div>
          )}
          <CheckButton style={{ display: "none" }} ref={checkBtn} />
        </Form>
      </div>
    </div>
  );
};

export default Register;
