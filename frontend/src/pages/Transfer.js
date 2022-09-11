import React, { useState, useRef } from "react";
import { useNavigate } from 'react-router-dom';
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";

import OptService from "../services/opt.service";

const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const Transfer = () => {
  let navigate = useNavigate();
  const form = useRef();
  const checkBtn = useRef();
  const [amount, setAmount] = useState("");
  const [payee, setPayee] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const onChangeAmount = (e) => {
    const amount = e.target.value;
    setAmount(amount);
  };

  const onChangePayee = (e) => {
    const payee = e.target.value;
    setPayee(payee);
  };

  const handleTransfer = (e) => {
    e.preventDefault();

    setMessage("");
    setLoading(true);

    form.current.validateAll();

    if (checkBtn.current.context._errors.length === 0) {
      OptService.transfer(amount, payee).then(
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
        <strong>Transfer Page</strong>
        </p>
        <Form onSubmit={handleTransfer} ref={form}>
          <div className="form-group">
            <label htmlFor="amount">Amount</label>
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
            <label htmlFor="payee">Payee</label>
            <Input
              type="text"
              className="form-control"
              name="payee"
              value={payee}
              onChange={onChangePayee}
              validations={[required]}
            />
          </div>

          <div className="form-group">
            <button className="btn btn-primary btn-block" disabled={loading}>
              {loading && (
                <span className="spinner-border spinner-border-sm"></span>
              )}
              <span> Transfer</span>
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

export default Transfer;
