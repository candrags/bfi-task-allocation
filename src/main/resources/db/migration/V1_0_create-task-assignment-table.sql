create table task_assignment
(
    id SERIAL PRIMARY KEY,
    transaction_code varchar(10) not null,
    module_name varchar(50) not null,
    task_name varchar(50) not null,
    task_description varchar(50) not null,
    link_url varchar(50) not null,
    assignment_type varchar(10) not null,
    assign_to varchar(20) not null,
    task_status varchar(10) not null,
    created_at timestamp not null,
    updated_at timestamp
);
