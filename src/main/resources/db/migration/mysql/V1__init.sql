CREATE TABLE `Livro` (
  `Id_livro` bigint(20) NOT NULL,
  `Titulo_livro` varchar(255) NOT NULL,
  `Nome_autor` varchar(255) ,
  `Nome_editora` varchar(255) ,
  `Preco_livro` double ,
  `Qtd_estoque` integer ,
  `Genero` varchar(255) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Autor` (
  `Id_autor` bigint(20) NOT NULL,
  `Cpf` varchar(255) NOT NULL,
  `Nome_autor` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Cliente` (
  `Id_cliente` bigint(20) NOT NULL,
  `Nome_cliente` varchar(255) NOT NULL,
  `Cpf_cliente` varchar(255) NOT NULL,
  `Telefone_cliente` varchar(255) DEFAULT NULL,
  `Email_cliente` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Venda` (
  `Id_venda` bigint(20) NOT NULL,
  `Dt_venda` datetime NOT NULL,
  `Valor_venda` double NOT NULL,
  `Titulo_Livro` varchar(255) DEFAULT NULL,
  `Nome_cliente` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--
-- Indexes for table `Livro`
--
ALTER TABLE `Livro`
  ADD PRIMARY KEY (`Id_livro`);
--
-- Indexes for table `Autor`
--
ALTER TABLE `Autor`
  ADD PRIMARY KEY (`Id_autor`);
--
-- Indexes for table `Cliente`
--
ALTER TABLE `Cliente`
  ADD PRIMARY KEY (`Id_cliente`);
--
-- Indexes for table `Venda`
--
ALTER TABLE `Venda`
  ADD PRIMARY KEY (`Id_venda`);

--
-- AUTO_INCREMENT for table `Livro`
--
ALTER TABLE `Livro`
  MODIFY `Id_livro` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Autor`
--
ALTER TABLE `Autor`
  MODIFY `Id_autor` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Cliente`
--
ALTER TABLE `Cliente`
  MODIFY `Id_cliente` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Venda`
--
ALTER TABLE `Venda`
  MODIFY `Id_venda` bigint(20) NOT NULL AUTO_INCREMENT;



