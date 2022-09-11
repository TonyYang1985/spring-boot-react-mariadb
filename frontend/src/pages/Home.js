import React, { useState, useEffect } from "react";
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import UserService from "../services/user.service";
import AuthService from "../services/auth.service";


const Home = () => {
  const [content, setContent] = useState("");
  const currentUser = AuthService.getCurrentUser();
  const [user, setUsers] = useState([]);

  useEffect(() => {
    UserService.getPublicContent().then(
      (response) => {
        setContent(JSON.stringify(response.data.users));
        setUsers(response.data.users)
      },
      (error) => {
        setContent(error);
      }
    );
  }, []);

 
  return (<div>
  <div className="card">
      <DataTable value={user} responsiveLayout="scroll">
          <Column field="id" header="ID"></Column>
          <Column field="username" header="Name"></Column>
          <Column field="email" header="Email"></Column>
      </DataTable>
  </div>
</div>);
};

export default Home;
