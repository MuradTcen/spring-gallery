truncate file_work;
truncate file_author;
truncate files;

insert into files(path,created_at)
select a.photo_path || ''/''||w.image_path || ''_normal.jpg'',current_timestamp from works w
join authors a on w.author_id=a.id;

insert into file_work(file_id,work_id)
select  f.id, w.id from  works w
join authors a on w.author_id=a.id
join files f on a.photo_path || ''/''||w.image_path || ''_normal.jpg''=f.path;

insert into files(path,created_at)
select a.photo_path || ''/author.jpg'',current_timestamp from authors a where a.photo_path notnull;

insert into file_author(file_id,author_id)
select f.id,a.id from  authors a
inner join files f on f.path=a.photo_path || ''/author.jpg'';

https://www.postgresqltutorial.com/postgresql-joins/