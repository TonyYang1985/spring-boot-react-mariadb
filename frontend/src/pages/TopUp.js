import React, { useState, useRef } from "react";
import { useNavigate } from 'react-router-dom';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";

import AuthService from "../services/auth.service";

const validateNumberField = myNumber => {
    const numberRegEx = /\-?\d*\.?\d{1,2}/;
    return numberRegEx.test(String(myNumber).toLowerCase());
  };

  

const required = (value) => {

  if (!value && validateNumberField(value) ) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is number &  required!
      </div>
    );
  }
};

const TopUp = () => {
  let navigate = useNavigate();
  const form = useRef();
  const checkBtn = useRef();
  const [amount, setAmount] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const onChangeAmount= (e) => {
    const amount = e.target.value;
    setAmount(amount);
  };


  const handleTopUp = (e) => {
    e.preventDefault();
    setMessage("");
    setLoading(true);
    form.current.validateAll();
    if (checkBtn.current.context._errors.length === 0) {
      AuthService.login(amount).then(
        () => {
          navigate("/home");
          window.location.reload();
        },
        (error) => {
          setLoading(false);
          setMessage(error);
        }
      );
    } else {
      setLoading(false);
    }
  };

  return (
    <div className="col-md-12">
      <div className="card card-container">
         <p>
        <strong>Top Up Page</strong>
        </p>
        <Form onSubmit={handleTopUp} ref={form}>
          <div className="form-group">
            <label htmlFor="amount">amount</label>
            <Input
              type="text"
              className="form-control"
              name="amount"
              value={amount}
              onChange={onChangeAmount}
              validations={[required]}
            />
          </div>

          <div className="form-group">
            <button className="btn btn-primary btn-block" disabled={loading}>
              {loading && (
                <span className="spinner-border spinner-border-sm"></span>
              )}
              <span>TopUp</span>
            </button>
          </div>

          {message && (
            <div className="form-group">
              <div className="alert alert-danger" role="alert">
                {message}
              </div>
            </div>
          )}
          <CheckButton style={{ display: "none" }} ref={checkBtn} />
        </Form>
      </div>
    </div>
  );
};

export default TopUp;
